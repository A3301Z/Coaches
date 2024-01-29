package Coaches.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.UUID;

@Component
public class CoachPhotoRepository {
    Environment environment;

    public CoachPhotoRepository(@Autowired Environment environment) {
        this.environment = environment;
    }

    public boolean isFileAlreadyUploaded(UUID coachId, String fileName) {
        // TODO: добавить код проверки наличия файла
        return false;
    }

    public void add(UUID coachId, String fileName, byte[] content) {
        try (Connection connection = connect();
             PreparedStatement statement = connection
                     .prepareStatement("insert into coach_image (id, coach_id, file_name, content, is_main)" +
                             " values (?,?,?,?,?);")) {

            statement.setObject(1, UUID.randomUUID());
            statement.setObject(2, coachId);
            statement.setString(3, fileName);
            statement.setBytes(4, content);
            statement.setBoolean(5, true);

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка добавления фотографии тренера.", e);
        }
    }

    public byte[] getMainPhoto(UUID coach_id) {
        try (Connection connection = connect();
             PreparedStatement statement = connection
                     .prepareStatement("select content from coach_image where coach_id = ? and is_main = true;")) {

            statement.setObject(1, coach_id);
            var result = statement.executeQuery();

            if (result != null) {
                result.next();
                return result.getBytes(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка добавления фотографии тренера.", e);
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
