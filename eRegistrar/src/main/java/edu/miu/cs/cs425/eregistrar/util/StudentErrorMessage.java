package edu.miu.cs.cs425.eregistrar.util;

public class StudentErrorMessage {
    private StudentErrorMessage() {}

    public static String studentNotFound(Long studentId) {
        return "Student with id #" + studentId + " not found.";
    }
}
