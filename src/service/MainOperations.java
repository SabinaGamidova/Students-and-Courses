package service;

import model.Category;
import model.Course;
import model.Student;

import java.util.List;

public interface MainOperations{
/*   List<Student> getAllStudents();
     boolean createStudent(Student s);
     List<Student> getStudentByGroup(int group);
     Student getStudentById(int id);
     boolean updateStudent(Student student);
     boolean deleteStudent(int id);
     boolean createCourse(Course course);
     List<Course> getAllCourses();
     boolean deleteCourse(int id);
     Course getCourseById(int id);
     boolean updateCourse(Course course);
     List<Course> getCourseByCategory(Category category);*/
     boolean addStudentOnCourse(Student student, Course course);
     boolean deleteStudentFromCourse(Student student, Course course);
}
