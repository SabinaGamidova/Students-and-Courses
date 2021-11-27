package service;

import db.StudentsDB;
import exceptions.GroupException;
import exceptions.StudentException;
import model.Course;
import model.Student;

import java.util.List;

public class StudentService implements StudentOperations{
    private StudentsDB db;

    public StudentService(){
        db = new StudentsDB();
    }

    @Override
    public void deleteStudent(int id) throws StudentException{
        db.deleteStudent(id);
    }

    @Override
    public List<Student> getAllStudents(){
        return db.getAllStudents();
    }


    @Override
    public Student createStudent(Student s) throws StudentException{
        validate(s);
        return db.createStudent(s);
    }


    @Override
    public List<Student> getStudentByGroup(int group) throws GroupException{
        if(group > 0 && group < 6) {
            return db.getStudentByGroup(group);
        }
        throw new GroupException("Группа студента написана некорректно.");
    }

    @Override
    public Student getStudentById(int id) throws StudentException{
         return db.getStudentById(id);
    }

    @Override
    public void updateStudent(Student student) throws StudentException{
        validate(student);
        db.updateStudent(student);
    }

    private void validate(Student s) throws StudentException{
        if(s == null){
            throw new StudentException("Студент == null.");
        }
        if(s.getFirstname() == null || s.getFirstname().isBlank()){
            throw new StudentException("Имя студента: " + s.getFirstname() + " написано некорректно.");
        }
        if(s.getSurname() == null || s.getSurname().isBlank()){
            throw new StudentException("Фамилия студента: " + s.getSurname() +" написана некорректно.");
        }
    }

    @Override
    public List<Course> getAllCoursesOfStudent(int id) throws StudentException{
        Student student = getStudentById(id);
        if(student != null){
            return student.getCourses();
        }
       return null;
    }
}
