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
    public void deleteStudent(int id){
        db.deleteStudent(id);
    }

    @Override
    public List<Student> getAllStudents(){
        return db.getAllStudents();
    }


    @Override
    public Student createStudent(Student s) {
        validate(s);
        return db.createStudent(s);
    }

    @Override
    public List<Student> getStudentByGroup(int group){
        if(group > 0 && group < 6) {
            return db.getStudentByGroup(group);
        }
        throw new RuntimeException("Группа студента написана некорректно.");
    }

    @Override
    public Student getStudentById(int id){
         return db.getStudentById(id);
    }

    @Override
    public void updateStudent(Student student){
        validate(student);
        db.updateStudent(student);
    }

    private void validate(Student s) {
        if(s == null){
            throw new RuntimeException("Студента == null.");
        }
        if(s.getFirstname() == null || s.getFirstname().isBlank()){
            throw new RuntimeException("Имя студента написано некорректно.");
        }
        if(s.getSurname() == null || s.getSurname().isBlank()){
            throw new RuntimeException("Фамилия студента написана некорректно.");
        }
        if (s.getGroup() < 0 || s.getGroup() > 6) {
            throw new RuntimeException("Группа студента написана некорректно.");
        }
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
