package service;

import db.CoursesDB;
import exceptions.CategoryException;
import exceptions.CourseException;
import model.Category;
import model.Course;
import model.Student;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CoursesService implements CourseOperations{
    private CoursesDB db;


    public CoursesService(){
        db = new CoursesDB();
    }

    @Override
    public Course createCourse(Course course)throws CourseException{
        validate(course);
        return db.create(course);
    }

    @Override
    public List<Course> getAllCourses(){
        return db.getAll();
    }

    @Override
    public void deleteCourse(int id) throws CourseException{
         db.delete(id);
    }


    @Override
    public Course getCourseById(int id) throws CourseException{
        return db.getById(id);
    }


    @Override
    public void updateCourse(Course course) throws CourseException {
        validate(course);
        db.update(course);
    }


    @Override
    public List<Course> getCourseByCategory(Category category) throws CategoryException{
        if(category != null) {
            return db.getByCategory(category);
        }
        throw new CategoryException("Категория курса выбрана некорректно.");
    }


    private void validate(Course c) throws CourseException{
        if(c == null){
            throw new CourseException("Курс == null.");
        }
        if(c.getName() == null || c.getName().isBlank()){
            throw new CourseException("Название курса " +c.getName()+ " написано некорректно.");

        }
        if(c.getTeacher() == null || c.getTeacher().isBlank()){
            throw new CourseException("Имя преподавателя курса " + c.getTeacher() +" написано некорректно.");

        }
    }

    @Override
    public List<Student> getAllStudentsOnCourse(int id) throws CourseException{
        Course course = getCourseById(id);
        if(course != null){
            return course.getStudents();
        }
        return null;
    }



}
