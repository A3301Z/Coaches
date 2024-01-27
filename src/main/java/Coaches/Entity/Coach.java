package Coaches.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.UUID;

@JsonPropertyOrder({"Id", "Firstname", "Secondname", "Age", "Birthday", "Phonenumber", "Email", "Archived"})
public class Coach {
    private final UUID Id;
    private final String Firstname;
    private final String Secondname;
    private final int Age;
    LocalDate Birthday;
    String PhoneNumber;
    String Email;
    Timestamp Archived;

    public Coach(UUID Id, String Firstname, String Secondname, int Age, LocalDate Birthday, String PhoneNumber,
                 String Email, Timestamp Archived) {
        this.Id = Id;
        this.Firstname = Firstname;
        this.Secondname = Secondname;
        this.Age = Age;
        this.Birthday = Birthday;
        this.PhoneNumber = PhoneNumber;
        this.Email = Email;
        this.Archived = Archived;
    }

    @JsonProperty("Id")
    public UUID getId() {
        return Id;
    }

    @JsonProperty("Firstname")
    public String getFirstname() {
        return Firstname;
    }

    @JsonProperty("Secondname")
    public String getSecondname() {
        return Secondname;
    }

    @JsonProperty("Age")
    public int getAge() {
        return Age;
    }

    @JsonProperty("Birthday")
    @JsonFormat(pattern = "dd.MM.yyyy")
    public LocalDate getBirthday() {
        return Birthday;
    }

    @JsonProperty("Phonenumber")
    public String getPhoneNumber() {
        return PhoneNumber;
    }

    @JsonProperty("Email")
    public String getEmail() {
        return Email;
    }

    @JsonProperty("Archived")
    public Timestamp getArchivedStatus() {
        return Archived;
    }

    @Override
    public String toString() {
        return String.format("""
                ID: %s
                Имя: %s
                Фамилия: %s
                Возраст: %d
                Дата рождения: %s
                Телефон: %s
                Имэйл: %s
                Статус Архивации: %b
                """, Id, Firstname, Secondname, Age, Birthday, PhoneNumber, Email, Archived);
    }
}
