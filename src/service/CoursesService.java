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
    public boolean createCourse(Course course){
        if(validate(course)){
            db.create(course);
            return true;
        }
        return false;
    }

    @Override
    public List<Course> getAllCourses(){
        return db.getAll();
    }

    @Override
    public boolean deleteCourse(int id){
        return db.delete(id);
    }


    @Override
    public Course getCourseById(int id){
        return db.getById(id);
    }


    @Override
    public boolean updateCourse(Course course){
        if(validate(course)) {
            return db.update(course);
        }
        return false;
    }


    @Override
    public List<Course> getCourseByCategory(Category category){
        if(category != null) {
            return db.getByCategory(category);
        }
        return new ArrayList<>();
    }


    private boolean validate(Course c) {
        if(c == null){
            return false;
        }
        if(c.getName() == null || c.getName().isBlank()){
            return false;
        }
        if(c.getTeacher() == null || c.getTeacher().isBlank()){
            return false;
        }
        if(c.getCategory() == null){
            return false;
        }
        return true;
    }


















}
