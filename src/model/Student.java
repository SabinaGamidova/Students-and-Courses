package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Student {
    private String firstname;
    private String surname;
    private int group;
    private int id;
    private List<Course> courses;


    public Student(){
    }

    public List<Course> getCourses(){
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public Student(String firstname, String surname, int group) {
        this.firstname = firstname;
        this.surname = surname;
        this.group = group;
        courses = new ArrayList<>();

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }


    @Override
    public String toString() {
        return firstname + " " + surname + ", группа " + group + ", ID = " + id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return group == student.group && id == student.id && Objects.equals(firstname, student.firstname) && Objects.equals(surname, student.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstname, surname, group, id);
    }
}
