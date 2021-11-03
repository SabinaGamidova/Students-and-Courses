package service;

import model.Student;
import java.util.List;

public interface StudentOperations {
     List<Student> getAllStudents();
     boolean createStudent(Student s);
     List<Student> getStudentByGroup(int group);
     Student getStudentById(int id);
     boolean updateStudent(Student student);
     boolean deleteStudent(int id);
}