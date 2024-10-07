package Coaches.Entity;

import Coaches.persistence.Models.CoachDto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Builder
@Table("Coaches")
public class Coach {

	@Id
	private UUID id;

	@Column("lastname")
	private String lastname;

	@Column("firstname")
	private String firstname;

	@Column("surname")
	private String surname;

	@Column("age")
	private int age;

	@Column("birthday")
	private LocalDate birthday;

	@Column("phone_number")
	private String phoneNumber;

	@Column("email")
	private String email;

	@Column("is_archived")
	private boolean isArchived;

	@Column("archived")
	private Timestamp archivingTime;

	public static Coach toCoach(CoachDto coachDto) {
		return Coach.builder()
				.lastname(coachDto.lastname)
				.firstname(coachDto.firstname)
				.surname(coachDto.surname)
				.age(coachDto.age)
				.birthday(coachDto.birthday)
				.phoneNumber(coachDto.phoneNumber)
				.email(coachDto.email)
				.isArchived(coachDto.isArchived)
				.archivingTime(coachDto.archivingTime)
				.build();
	}

}
