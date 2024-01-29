package Coaches.Repository;

import Coaches.Entity.Coach;
import Coaches.Exceptions.CoachNotFoundException;
import Coaches.Models.CoachDto;
import Coaches.SQL_Queries.Queries;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class CoachRepository {
    private static final Logger logger = LoggerFactory.getLogger(CoachRepository.class);
    private final Environment environment;

    public CoachRepository(@Autowired Environment environment) {
        this.environment = environment;
    }

    public List<Coach> getAllCoaches() {

        List<Coach> coachList = new ArrayList<>();
        String SELECT_COACH_FROM_DB = "SELECT * FROM coach";
        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(Queries.SELECT_COACH_FROM_DB.getQuery());
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                coachList.add(new Coach(UUID.fromString(resultSet.getString("id")),
                        resultSet.getString("firstname"),
                        resultSet.getString("secondname"),
                        resultSet.getInt("age"),
                        resultSet.getDate("birthday").toLocalDate(),
                        resultSet.getString("phonenumber"),
                        resultSet.getString("email"),
                        resultSet.getTimestamp("archived")));
            }
        } catch (SQLException e) {
            logger.error("Ошибка получения списка тренеров. Метод: getAllCoaches", e);
            throw new RuntimeException("Ошибка подключения к БД.", e);
        }
        logger.info("Список тренеров успешно получен. Метод: getAllCoaches \n");
        return coachList;
    }

    public Optional<Coach> getById(UUID id) {
        logger.info("Тренер с id {} найден. Метод: getById", id);
        return getAllCoaches().stream().filter(x -> x.getId().equals(id)).findFirst();
    }

    public void updateArchivedStatus(UUID id) throws CoachNotFoundException {
        try (Connection connection = connect();
             PreparedStatement statement = connection.prepareStatement(Queries.UPDATE_ARCHIVED_STATUS_SQL.getQuery())) {

            statement.setObject(1, getDateTime());
            statement.setObject(2, id);

            int rowsUpdated = statement.executeUpdate();
            System.out.println(getDateTime());
            if (rowsUpdated == 0) {
                logger.error("Тренер с id {} не найден. Метод: updateArchivedStatus", id);
                throw new CoachNotFoundException("Тренер с id " + id + " не найден");
            }
        } catch (SQLException e) {
            logger.error("Не удалось обновить статус архивации.", e);
        }
    }

    public void add(Coach coach) {
        try (Connection connection = connect();
             PreparedStatement statement = connection.prepareStatement(Queries.ADD_NEW_COACH_SQL.getQuery())) {

            statement.setObject(1, UUID.randomUUID());
            statement.setString(2, coach.getFirstname());
            statement.setString(3, coach.getSecondname());
            statement.setInt(4, coach.getAge());
            statement.setDate(5, Date.valueOf(coach.getBirthday()));
            statement.setString(6, coach.getPhoneNumber());
            statement.setString(7, coach.getEmail());
            statement.setTimestamp(8, coach.getArchivedStatus());
            statement.executeUpdate();

        } catch (SQLException e) {
            logger.info("Ошибка добавления тренера.", e);
        }
        logger.info("Тренер успешно добавлен. Метод: add \n");
    }

    public void updateCoach(CoachDto coachDto) {
        try (Connection connection = connect();
             PreparedStatement statement = connection.prepareStatement(Queries.UPDATE_COACH_FIELD.getQuery())) {

            statement.setObject(8, coachDto.Id);
            statement.setString(1, coachDto.Firstname);
            statement.setString(2, coachDto.Secondname);
            statement.setInt(3, coachDto.Age);
            statement.setDate(4, Date.valueOf(coachDto.Birthday));
            statement.setString(5, coachDto.Phonenumber);
            statement.setString(6, coachDto.Email);
            statement.setTimestamp(7, coachDto.Archived);
            statement.executeUpdate();

        } catch (SQLException e) {
            logger.error("Ошибка обновления полей тренера.", e);
        }
        logger.info("Поля тренера успешно обновлены. Метод: updateCoach \n");
    }

    public void deletedByID(UUID id) {
        try (Connection connection = connect();
             PreparedStatement statement = connection.prepareStatement(Queries.DELETED_COACH.getQuery())) {
            statement.setObject(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Ошибка удаления.", e);
        }
        logger.info("Тренер успешно удален. Метод: deletedByID \n");
    }

    public Connection connect() {

        String url = environment.getProperty("db.url");
        String name = environment.getProperty("db.username");
        String pass = environment.getProperty("db.password");
        logger.info("Попытка подключения к БД.");
        try {
            assert url != null;
            logger.info("Соединение с БД установлено.");
            return DriverManager.getConnection(url, name, pass);

        } catch (SQLException e) {
            logger.error("Не удалось подключиться к БД.", e);
        }
        return null;
    }

    public Timestamp getDateTime() {
        LocalDateTime localDateTime = LocalDateTime.now();
        ZonedDateTime utcDateTime = ZonedDateTime.of(localDateTime, ZoneId.of("UTC"));
        return Timestamp.valueOf(utcDateTime.toLocalDateTime());
    }
}
