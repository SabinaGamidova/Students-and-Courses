package service;

import model.Category;
import model.Course;
import model.Student;

import java.util.List;

public interface MainOperations{
     boolean addStudentOnCourse(int studentId, int courseId);
     boolean deleteStudentFromCourse(int studentId, int courseId);
}
