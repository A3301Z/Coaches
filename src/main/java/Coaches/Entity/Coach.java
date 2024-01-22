package Coaches.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@JsonPropertyOrder({"Id", "Firstname", "Secondname", "Age", "Birthday", "Phonenumber", "Email", "Archived"})
public class Coach {
    private UUID Id;
    private String Firstname;
    private String Secondname;
    private int Age;
    LocalDate Birthday;
    String PhoneNumber;
    String Email;
    boolean Archived;

    public Coach() {
    }

    public Coach(UUID Id, String Firstname, String Secondname, int Age) {
        this.Id         = Id;
        this.Firstname  = Firstname;
        this.Secondname = Secondname;
        this.Age        = Age;
    }

    public Coach(UUID Id, String Firstname, String Secondname, int Age, LocalDate Birthday, String PhoneNumber,
            String Email, boolean Archived) {
        this.Id          = Id;
        this.Firstname   = Firstname;
        this.Secondname  = Secondname;
        this.Age         = Age;
        this.Birthday    = Birthday;
        this.PhoneNumber = PhoneNumber;
        this.Email       = Email;
        this.Archived    = Archived;
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
    public boolean getArchivedStatus() {
        return Archived;
    }

    public void setFirstname(String firstname) {
        this.Firstname = firstname;
    }

    public void setSecondname(String surname) {
        this.Secondname = surname;
    }

    public void setAge(int age) {
        this.Age = age;
    }

    public void setBirthday(LocalDate birthday) {
        this.Birthday = birthday;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.PhoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    public void setId(UUID id) {
        this.Id = id;
    }

    public void setArchived() {
        this.Archived = true;
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
