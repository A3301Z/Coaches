package Coaches.Models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public class CoachDto extends CoachMinimalDto {
    @NotBlank(message = "Email is mandatory")
    @jakarta.validation.constraints.Email
    public String Email;
    public LocalDate Birthday;

    @NotBlank(message = "Phone number is mandatory")
    public String PhoneNumber;
}
