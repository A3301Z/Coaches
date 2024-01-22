package Coaches.Models;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class CoachDto extends CoachMinimalDto {
    @jakarta.validation.constraints.Email
    public String Email;
    @JsonFormat(pattern = "dd.MM.yyyy")
    public LocalDate Birthday;
    public String Phonenumber;
}
