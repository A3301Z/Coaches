package Coaches.Repository;

import Coaches.Components.DatabaseConfig;
import Coaches.Entity.Coach;
import Coaches.Exceptions.CoachNotFoundException;
import Coaches.Models.CoachDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class CoachRepository {
    DatabaseConfig config = new DatabaseConfig();
    public List<Coach> getAllCoaches() {

        List<Coach> coachList = new ArrayList<>();
        String SELECT_COACH_FROM_DB = "SELECT * FROM coach";
        try (Connection connection = DriverManager.getConnection(config.getUrl(),
                                                                 config.getUsername(),
                                                                 config.getPassword());
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_COACH_FROM_DB);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                coachList.add(new Coach(UUID.fromString(resultSet.getString("id")),
                                        resultSet.getString("firstname"),
                                        resultSet.getString("secondname"),
                                        resultSet.getInt("age"),
                                        resultSet.getDate("birthday").toLocalDate(),
                                        resultSet.getString("phonenumber"),
                                        resultSet.getString("email"),
                                        resultSet.getBoolean("archived")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return coachList;
    }

    public Optional<Coach> getById(UUID id) {
        return getAllCoaches().stream().filter(x -> x.getId().equals(id)).findFirst();
    }

    public void updateArchivedStatus(UUID id) throws CoachNotFoundException {

        String UPDATE_ARCHIVED_STATUS_SQL = "UPDATE coach SET archived = true WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(config.getUrl(),
                                                                 config.getUsername(),
                                                                 config.getPassword());
             PreparedStatement statement = connection.prepareStatement(UPDATE_ARCHIVED_STATUS_SQL)) {

            statement.setObject(1, id);

            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated == 0) {
                throw new CoachNotFoundException("Тренер с id " + id + " не найден");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Не удалось обновить статус архивации", e);
        }
    }

    public void add(Coach coach) {

        String ADD_NEW_COACH_SQL =
                "INSERT INTO coach (id, firstname, secondname, age, birthday, phonenumber, email, archived)\n" +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
        try (Connection connection = DriverManager.getConnection(config.getUrl(),
                                                                 config.getUsername(),
                                                                 config.getPassword());
             PreparedStatement statement = connection.prepareStatement(ADD_NEW_COACH_SQL)) {

            statement.setObject(1, UUID.randomUUID());
            statement.setString(2, coach.getFirstname());
            statement.setString(3, coach.getSecondname());
            statement.setInt(4, coach.getAge());
            statement.setDate(5, Date.valueOf(coach.getBirthday()));
            statement.setString(6, coach.getPhoneNumber());
            statement.setString(7, coach.getEmail());
            statement.setBoolean(8, coach.getArchivedStatus());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка добавления нового тренера.", e);
        }
    }

    public void updateCoach(CoachDto coachDto) {

        String UPDATE_COACH_FIELD = """
                UPDATE coach SET
                firstname = ?,
                secondname = ?,
                age = ?,
                birthday = ?,
                phonenumber = ?,
                email = ?
                WHERE id = ?""";
        try (Connection connection = DriverManager.getConnection(config.getUrl(),
                                                                 config.getUsername(),
                                                                 config.getPassword());
             PreparedStatement statement = connection.prepareStatement(UPDATE_COACH_FIELD)) {

            statement.setObject(7, coachDto.Id);
            statement.setString(1, coachDto.Firstname);
            statement.setString(2, coachDto.Secondname);
            statement.setInt(3, coachDto.Age);
            statement.setDate(4, Date.valueOf(coachDto.Birthday));
            statement.setString(5, coachDto.Phonenumber);
            statement.setString(6, coachDto.Email);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка добавления нового тренера.", e);
        }
    }
}
