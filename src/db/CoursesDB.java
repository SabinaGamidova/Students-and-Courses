package db;

import model.Course;
import model.Student;
import model.Category;

import java.util.ArrayList;
import java.util.List;

public class CoursesDB {
    private List<Course>courses;

    public CoursesDB(){
        courses = new ArrayList<>();
    }

    public List<Course> getAll(){
        return courses;
    }

    public void create(Course course){
        course.setId(generateId((int) (Math.random() * 10000000)));
        courses.add(course);
    }

    private int generateId(int id){
        for (Course course: courses) {
            if(course.getId() == id){
                return generateId((int) (Math.random() * 10000000));
            }
        }
        return id;
    }


    public boolean delete(int id){
        for (Course course: courses) {
            if(course.getId() == id){
                return courses.remove(course);
            }
        }
        return false;
    }


    public Course getById(int id) {
        for (Course course: courses) {
            if(course.getId() == id){
                return course;
            }
        }
        return null;
    }


    public boolean update(Course course) {
        for (Course item: courses) {
            if(course.getId() == item.getId()) {
                courses.remove(item);
                courses.add(course);
                return true;
            }
        }
        return false;
    }

    public List<Course> getByCategory(Category category){
        List<Course>items = new ArrayList<>();
        for (Course c : courses) {
            if(c.getCategory().equals(category)){
                items.add(c);
            }
        }
        return items;
    }



}
