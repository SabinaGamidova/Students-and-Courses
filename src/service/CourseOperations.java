package service;

import exceptions.CategoryException;
import exceptions.CourseException;
import exceptions.GroupException;
import exceptions.StudentException;
import model.Category;
import model.Course;
import model.Student;

import java.util.ArrayList;
import java.util.List;

public interface CourseOperations {
     Course createCourse(Course course) throws CourseException, CategoryException;
     List<Course> getAllCourses();
     void deleteCourse(int id) throws CourseException, StudentException, GroupException, CategoryException;
     Course getCourseById(int id) throws CourseException;
     void updateCourse(Course course) throws CourseException, CategoryException;
     List<Course> getCourseByCategory(Category category) throws CategoryException;
     List<Student> getAllStudentsOnCourse(int id) throws CourseException;
}
