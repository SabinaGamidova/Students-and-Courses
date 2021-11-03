package service;

import model.Category;
import model.Course;
import model.Student;

import java.util.List;

public interface MainOperations{
     boolean addStudentOnCourse(Student student, Course course);
     boolean deleteStudentFromCourse(Student student, Course course);
}
