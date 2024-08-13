package edu.miu.cs.cs425.eregistrar.service.serviceImpl;

import edu.miu.cs.cs425.eregistrar.dto.StudentRequestDto;
import edu.miu.cs.cs425.eregistrar.dto.StudentResponseDto;
import edu.miu.cs.cs425.eregistrar.exception.ResourceNotFoundException;
import edu.miu.cs.cs425.eregistrar.model.Student;
import edu.miu.cs.cs425.eregistrar.repository.StudentRepository;
import edu.miu.cs.cs425.eregistrar.service.StudentService;
import edu.miu.cs.cs425.eregistrar.util.StudentErrorMessage;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Page<StudentResponseDto> getAllStudents(Pageable pageable) {
        return studentRepository.findAll(pageable)
                              .map(student -> modelMapper.map(student, StudentResponseDto.class));
    }

    @Override
    public  Page<StudentResponseDto> search(String keyword, Pageable pageable) {
        return studentRepository.searchStudentsByKeyword(keyword, pageable)
                              .map(event -> modelMapper.map(event, StudentResponseDto.class));
    }

    @Override
    public StudentResponseDto getStudentById(Long studentId) {
        Student student = studentRepository.findById(studentId).orElse(null);
        if (student != null) {
            return modelMapper.map(student, StudentResponseDto.class);
        } else {
            throw new ResourceNotFoundException(StudentErrorMessage.studentNotFound(studentId));
        }
    }

    @Override
    public StudentRequestDto createStudent(StudentRequestDto student) {
        Student stud = modelMapper.map(student, Student.class);
        return modelMapper.map(studentRepository.save(stud), StudentRequestDto.class);
    }

    @Override
    public StudentRequestDto updateStudent(StudentRequestDto student, Long studentId) {
        Student existingStudent = studentRepository.findById(studentId).orElse(null);
        if (existingStudent != null) {
            existingStudent.setTranscript(student.getTranscript());
            modelMapper.map(student, existingStudent);
            return modelMapper.map(studentRepository.save(existingStudent), StudentRequestDto.class);
        }
        throw new ResourceNotFoundException(StudentErrorMessage.studentNotFound(studentId));
    }

    @Override
    public void deleteStudent(Long studentId) {
        Student existingStudent = studentRepository.findById(studentId).orElse(null);
        if (existingStudent != null) {
            studentRepository.delete(existingStudent);
        } else {
            throw new ResourceNotFoundException(StudentErrorMessage.studentNotFound(studentId));
        }
    }


}
