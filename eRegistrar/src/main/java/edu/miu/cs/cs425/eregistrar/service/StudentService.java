package edu.miu.cs.cs425.eregistrar.service;

import edu.miu.cs.cs425.eregistrar.dto.StudentRequestDto;
import edu.miu.cs.cs425.eregistrar.dto.StudentResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StudentService {
    public Page<StudentResponseDto> getAllStudents(Pageable pageable);
      public StudentResponseDto getStudentById(Long id);
    public StudentRequestDto createStudent(StudentRequestDto student);
    public StudentRequestDto updateStudent(StudentRequestDto student, Long id);
    public void deleteStudent(Long studentId);
    public Page<StudentResponseDto> search(String keyword, Pageable pageable);
}
