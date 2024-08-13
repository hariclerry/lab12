package edu.miu.cs.cs425.eregistrar.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Student {
    @Id
    @GeneratedValue
    private Long      studentId;
    @Column(nullable = false)
    private String    studentNumber;
    @Column(nullable = false)
    private String    firstName;
    @Column(nullable = false)
    private String    lastName;
    @Column(nullable = true)
    private String    middleName;
    @Column(nullable = false)
    private double    cgpa;
    @Column(nullable = false)
    private LocalDate dateOfEnrollment;
    @Column(nullable = false)
    private boolean isInternational;

    @OneToOne(cascade={CascadeType.ALL})
    private Transcript transcript;
    public Student() {}
    public Student(
            String studentNumber,
            String firstName,
            String lastName,
            String middleName,
            double cgpa,
            LocalDate dateOfEnrollment,
            boolean isInternational
                  ) {
        this.studentNumber = studentNumber;
        this.firstName     = firstName;
        this.lastName      = lastName;
        this.middleName    = middleName;
        this.cgpa          = cgpa;
        this.dateOfEnrollment = dateOfEnrollment;
        this.isInternational = isInternational;
    }


}
