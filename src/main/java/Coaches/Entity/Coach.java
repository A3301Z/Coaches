package Coaches.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

public class Coach {
    private UUID id;
    private String firstName;
    private String secondName;
    private int age;
    LocalDate birthday;
    String phoneNumber;
    String email;

    public Coach(UUID id, String firstName, String secondName, int age) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.age = age;
    }

    public Coach(UUID id, String firstName, String secondName, int age, LocalDate birthday, String phoneNumber, String email) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.age = age;
        this.birthday = birthday;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    @JsonProperty("id")
    public UUID getId() {
        return id;
    }

    @JsonProperty("firstname")
    public String getFirstName() {
        return firstName;
    }

    @JsonProperty("secondName")
    public String getSecondName() {
        return secondName;
    }

    @JsonProperty("age")
    public int getAge() {
        return age;
    }

    @JsonProperty("birthday")
    public LocalDate getBirthday() {
        return birthday;
    }

    @JsonProperty("phone-number")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSurname(String surname) {
        this.secondName = surname;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
