package db;

import exceptions.CategoryException;
import exceptions.CourseException;
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

    public Course create(Course course){
        course.setId(generateId((int) (Math.random() * 10000000)));
        courses.add(course);
        return course;
    }

    private int generateId(int id){
        for (Course course: courses) {
            if(course.getId() == id){
                return generateId((int) (Math.random() * 10000000));
            }
        }
        return id;
    }


    public void delete(int id) throws CourseException {
        for (Course course: courses) {
            if(course.getId() == id){
                courses.remove(course);
                return;
            }
        }
        throw new CourseException(id, "Курса с таким id не существует.");
    }


    public Course getById(int id) throws CourseException{
        for (Course course: courses) {
            if(course.getId() == id){
                return course;
            }
        }
        throw new CourseException(id, "Курса с таким id не существует.");
    }


    public void update(Course course) throws CourseException{
        for (Course item: courses) {
            if(course.getId() == item.getId()) {
                courses.remove(item);
                courses.add(course);
                return;
            }
        }
        throw new CourseException(course.getId(), course.getName(), "Курса с такими данными не существует.");
    }

    public List<Course> getByCategory(Category category){
        List<Course>items = new ArrayList<>();
            for (Course c : courses) {
                if (c.getCategory().equals(category)) {
                    items.add(c);
                }
            }
            return items;
    }



}
