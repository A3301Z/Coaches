package Coaches.Services;

import Coaches.Entity.Coach;
import Coaches.Exceptions.CoachNotFoundException;
import Coaches.Models.CoachDto;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.sql.Date;
import java.util.*;

@Service
public class CoachesServices {
    public CoachesServices() {
    }

    public List<Coach> getAllCoaches() {
        String jdbcUrl = "jdbc:postgresql://localhost:5432/coaches";
        String username = "postgres";
        String password = "0000";

        List<Coach> coachList = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
            String query = "SELECT * FROM coach";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

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
            resultSet.close();
            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return coachList;
    }

    public Optional<Coach> getById(UUID id) {
        return getAllCoaches().stream().filter(x -> x.getId().equals(id)).findFirst();
    }

    public void updateArchivedStatus(UUID id) throws CoachNotFoundException {
        String jdbcUrl = "jdbc:postgresql://localhost:5432/coaches";
        String username = "postgres";
        String password = "0000";
        String updateQuery = "UPDATE coach SET archived = true WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
             PreparedStatement statement = connection.prepareStatement(updateQuery)) {

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

        String jdbcUrl = "jdbc:postgresql://localhost:5432/coaches";
        String username = "postgres";
        String password = "0000";

        String updateQuery =
                "INSERT INTO coach (id, firstname, secondname, age, birthday, phonenumber, email, archived)\n" +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
             PreparedStatement statement = connection.prepareStatement(updateQuery)) {

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
        String jdbcUrl = "jdbc:postgresql://localhost:5432/coaches";
        String username = "postgres";
        String password = "0000";

        String sql = """
                UPDATE coach SET
                firstname = ?,
                secondname = ?,
                age = ?,
                birthday = ?,
                phonenumber = ?,
                email = ?
                WHERE id = ?""";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
             PreparedStatement statement = connection.prepareStatement(sql)) {

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
