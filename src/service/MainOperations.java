package service;

import exceptions.CategoryException;
import exceptions.CourseException;
import exceptions.GroupException;
import exceptions.StudentException;
import model.Category;
import model.Course;
import model.Student;

import java.util.List;

public interface MainOperations{
     void addStudentOnCourse(int studentId, int courseId) throws CourseException, StudentException, GroupException, CategoryException;
     void deleteStudentFromCourse(int studentId, int courseId) throws CourseException, StudentException, GroupException, CategoryException;
}
