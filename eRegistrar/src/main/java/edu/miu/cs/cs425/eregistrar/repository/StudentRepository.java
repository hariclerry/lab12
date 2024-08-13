package edu.miu.cs.cs425.eregistrar.repository;

import edu.miu.cs.cs425.eregistrar.dto.StudentResponseDto;
import edu.miu.cs.cs425.eregistrar.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query(
            "SELECT s FROM Student s WHERE s.firstName LIKE %:keyword% OR s.lastName LIKE " +
            "%:keyword% OR s.studentNumber LIKE %:keyword%"
    )
    Page<Student> searchStudentsByKeyword(@Param("keyword") String keyword, Pageable pageable);
}
