package edu.miu.cs.cs425.eregistrar.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentResponseDto {
    private Long studentId;
    private String    studentNumber;

    private String    firstName;

    private String    lastName;

    private String    middleName;

    private double    cgpa;

    private LocalDate dateOfEnrollment;

    private boolean   isInternational;

    private TranscriptResponseDto transcript;
}
