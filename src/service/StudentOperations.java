package service;

import model.Course;
import model.Student;
import java.util.List;

public interface StudentOperations {
     List<Student> getAllStudents();
     Student createStudent(Student s);
     List<Student> getStudentByGroup(int group);
     Student getStudentById(int id);
     void updateStudent(Student student);
     void deleteStudent(int id);
     public List<Course> getAllCoursesOfStudent(int id);
}
