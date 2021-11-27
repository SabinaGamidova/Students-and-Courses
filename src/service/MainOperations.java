package service;

import exceptions.CourseException;
import exceptions.StudentException;
import model.Category;
import model.Course;
import model.Student;

import java.util.List;

public interface MainOperations{
     void addStudentOnCourse(int studentId, int courseId) throws CourseException, StudentException;
     void deleteStudentFromCourse(int studentId, int courseId) throws CourseException, StudentException;
}
