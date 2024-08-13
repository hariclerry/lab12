package edu.miu.cs.cs425.eregistrar.controller;

import edu.miu.cs.cs425.eregistrar.dto.StudentRequestDto;
import edu.miu.cs.cs425.eregistrar.dto.StudentResponseDto;
import edu.miu.cs.cs425.eregistrar.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v1/students")
public class Student {
    @Autowired
    private StudentService studentService;

    @GetMapping
    public ResponseEntity<Page<StudentResponseDto>> getStudents(Pageable pageable) {
        return ResponseEntity.ok(studentService.getAllStudents(pageable));

    }

    @GetMapping("/search")
    public ResponseEntity<Page<StudentResponseDto>> searchStudents(@RequestParam String keyword, Pageable pageable) {
        return ResponseEntity.ok(studentService.search(keyword, pageable));
    }
    @GetMapping("/{id}")
    public ResponseEntity<StudentResponseDto> getStudentById(@PathVariable("id") Long id) {
        return  ResponseEntity.ok(studentService.getStudentById(id));
    }

    @PostMapping
    public ResponseEntity<StudentRequestDto> createStudent(@RequestBody StudentRequestDto student) {
        return new ResponseEntity<>(studentService.createStudent(student), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentRequestDto> updateStudent(@PathVariable("id") Long id, @RequestBody StudentRequestDto student) {
        return new ResponseEntity<>(studentService.updateStudent(student, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") Long id) {
        studentService.deleteStudent(id);
        return new ResponseEntity<>("Student deleted successfully", HttpStatus.OK);
    }

}
