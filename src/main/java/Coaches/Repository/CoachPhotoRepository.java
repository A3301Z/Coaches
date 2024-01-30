package Coaches.Repository;

import Coaches.SQL_Queries.Queries;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.UUID;

@Component
public class CoachPhotoRepository {
    private final Logger logger = LoggerFactory.getLogger(CoachPhotoRepository.class);
    Environment environment;

    public CoachPhotoRepository(@Autowired Environment environment) {
        this.environment = environment;
    }

    public boolean isFileAlreadyUploaded(UUID coachId, String fileName) {
        boolean result = false;
        try (Connection connection = connect();
             PreparedStatement statement = connection.prepareStatement(Queries.PHOTO_IS_EXISTS.getQuery())) {
            statement.setObject(1, coachId);
            statement.setString(2, fileName);
            ResultSet resultSet = statement.executeQuery();
            result = resultSet.next();
        } catch (SQLException e) {
            logger.error("Ошибка запроса при попытке получить информацию о наличии фото.\n", e);
        }
        return result;
    }

    public void add(UUID coachId, String fileName, byte[] content, boolean is_main) {

        String info = "Фото успешно добавлено\n";

        if (isFileAlreadyUploaded(coachId, fileName)) {
            throw new RuntimeException(String.format("Фото с именем %s уже существует.\n", fileName));
        } else {
            try (Connection connection = connect();
                 PreparedStatement statement = connection.prepareStatement(Queries.ADD_PHOTO.getQuery())) {

                if (is_main) {
                    logger.info("Отправка запроса на изменение статуса старого фото.\n");
                    PreparedStatement statement_2 = connection.prepareStatement(Queries.DO_ALL_NOT_MAIN.getQuery());
                    statement_2.setObject(1, coachId);
                    statement_2.executeUpdate();
                    logger.info("Статус is_main старого фото успешно изменен.\n");
                    info = "Главное фото успешно добавлено.\n";
                }
                statement.setObject(1, UUID.randomUUID());
                statement.setObject(2, coachId);
                statement.setString(3, fileName);
                statement.setBytes(4, content);
                statement.setBoolean(5, is_main);
                statement.executeUpdate();
            } catch (SQLException e) {
                logger.error("Ошибка добавления фотографии тренера.\n");
                throw new RuntimeException("Ошибка добавления фотографии тренера.\n", e);
            }
        }
        logger.info(info);
    }

    public byte[] getMainPhoto(UUID coach_id) {
        try (Connection connection = connect();
             PreparedStatement statement = connection.prepareStatement(Queries.GET_MAIN_PHOTO.getQuery())) {

            statement.setObject(1, coach_id);
            ResultSet result = statement.executeQuery();

            if (result != null) {
                result.next();
                return result.getBytes(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка добавления фотографии тренера.\n", e);
        }
        return new byte[0];
    }

    public Connection connect() {

        String url = environment.getProperty("db.url");
        String name = environment.getProperty("db.username");
        String pass = environment.getProperty("db.password");

        try {
            assert url != null;
            return DriverManager.getConnection(url, name, pass);

        } catch (SQLException e) {
            throw new RuntimeException("Не удалось подключиться к БД.", e);
        }
    }
}
