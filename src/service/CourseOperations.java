package service;

import model.Category;
import model.Course;
import model.Student;

import java.util.ArrayList;
import java.util.List;

public interface CourseOperations {
     boolean createCourse(Course course);
     List<Course> getAllCourses();
     boolean deleteCourse(int id);
     Course getCourseById(int id);
     boolean updateCourse(Course course);
     List<Course> getCourseByCategory(Category category);
     List<Student> getAllStudentsOnCourse(int id);
}
