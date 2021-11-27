package service;

import exceptions.CourseException;
import exceptions.GroupException;
import exceptions.StudentException;
import model.Course;
import model.Student;
import java.util.List;

public interface StudentOperations {
     List<Student> getAllStudents();
     Student createStudent(Student s) throws StudentException;
     List<Student> getStudentByGroup(int group) throws GroupException;
     Student getStudentById(int id) throws StudentException;
     void updateStudent(Student student) throws StudentException;
     void deleteStudent(int id) throws StudentException, CourseException;
     public List<Course> getAllCoursesOfStudent(int id) throws StudentException;
}
