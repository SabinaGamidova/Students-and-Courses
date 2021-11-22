package service;

import model.Category;
import model.Course;
import model.Student;

import java.util.ArrayList;
import java.util.List;

public interface CourseOperations {
     Course createCourse(Course course);
     List<Course> getAllCourses();
     void deleteCourse(int id);
     Course getCourseById(int id);
     void updateCourse(Course course);
     List<Course> getCourseByCategory(Category category);
     List<Student> getAllStudentsOnCourse(int id);
}
