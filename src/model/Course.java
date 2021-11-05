package model;

import java.util.ArrayList;
import java.util.List;

public class Course {
    private String name;
    private Category category;
    private String teacher;
    private List<Student> students;
    private int id;

    public Course(String name, Category category, String teacher) {
        this.name = name;
        this.category = category;
        this.teacher = teacher;
        students = new ArrayList<>();
    }

    public Course(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return  "Название = " + name + ", категория = " + category + ", преподаватель = " + teacher + ", id = " + id;
    }
}
