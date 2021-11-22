package service;

import db.CoursesDB;
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
    public Course createCourse(Course course){
        validate(course);
        return db.create(course);
    }

    @Override
    public List<Course> getAllCourses(){
        return db.getAll();
    }

    @Override
    public void deleteCourse(int id){
         db.delete(id);
    }


    @Override
    public Course getCourseById(int id){
        return db.getById(id);
    }


    @Override
    public void updateCourse(Course course){
        validate(course);
        db.update(course);
    }


    @Override
    public List<Course> getCourseByCategory(Category category){
        if(category != null) {
            return db.getByCategory(category);
        }
        return new ArrayList<>();
    }


    private void validate(Course c) {
        if(c == null){
            throw new RuntimeException("Курс == null.");
        }
        if(c.getName() == null || c.getName().isBlank()){
            throw new RuntimeException("Название курса написано некорректно.");

        }
        if(c.getTeacher() == null || c.getTeacher().isBlank()){
            throw new RuntimeException("Имя преподавателя курса написано некорректно.");

        }
        if(c.getCategory() == null){
            throw new RuntimeException("Категория курса выбрана некорректно.");
        }
    }

    @Override
    public List<Student> getAllStudentsOnCourse(int id){
        Course course = getCourseById(id);
        if(course != null){
            return course.getStudents();
        }
        return null;
    }



}
