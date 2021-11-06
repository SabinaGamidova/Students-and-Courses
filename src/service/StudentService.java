package service;

import db.StudentsDB;
import model.Course;
import model.Student;

import java.util.List;

public class StudentService implements StudentOperations{
    private StudentsDB db;

    public StudentService(){
        db = new StudentsDB();
    }

    @Override
    public boolean deleteStudent(int id){
        return db.deleteStudent(id);
    }

    @Override
    public List<Student> getAllStudents(){
        return db.getAllStudents();
    }


    @Override
    public boolean createStudent(Student s) {
        if(validate(s)){
            db.createStudent(s);
            return true;
        }
        return false;
    }

    @Override
    public List<Student> getStudentByGroup(int group){
        if(group > 0 && group < 6) {
            return db.getStudentByGroup(group);
        }
        return null;
    }

    @Override
    public Student getStudentById(int id){
         return db.getStudentById(id);
    }

    @Override
    public boolean updateStudent(Student student){
        if(validate(student)) {
            return db.updateStudent(student);
        }
        return false;
    }

    private boolean validate(Student s) {
        if(s == null){
            return false;
        }
        if(s.getFirstname() == null || s.getFirstname().isBlank()){
            return false;
        }
        if(s.getSurname() == null || s.getSurname().isBlank()){
            return false;
        }
        if (s.getGroup() < 0 || s.getGroup() > 6) {
            return false;
        }
        return true;
    }

    @Override
    public List<Course> getAllCoursesOfStudent(int id){
        Student student = getStudentById(id);
        if(student != null){
            return student.getCourses();
        }
       return null;
    }
}
