package edu.miu.cs.cs425.eregistrar.dto;

import edu.miu.cs.cs425.eregistrar.model.Transcript;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentRequestDto {

    private String    studentNumber;
    @NotNull(message = "First Name cannot be null.")
    @NotBlank(message = "First Name cannot be blank.")
    @Size(min = 2, max = 30, message = "First name must be between 3 and 30 characters long.")
    private String    firstName;
    @NotNull(message = "Last Name cannot be null.")
    @NotBlank(message = "Last Name cannot be blank.")
    @Size(min = 2, max = 30, message = "Last name must be between 3 and 30 characters long.")
    private String    lastName;

    private String    middleName;

    private double    cgpa;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Enrollment date cannot be blank")
    private LocalDate dateOfEnrollment;

    private Transcript transcript;

    private boolean   isInternational;
}
