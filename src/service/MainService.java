package service;

import model.Category;
import model.Course;
import model.Student;

import java.util.List;

public class MainService implements MainOperations, StudentOperations, CourseOperations{
    private StudentOperations studentOperations;
    private CourseOperations courseOperations;


    public MainService() {
        studentOperations = new StudentService();
        courseOperations = new CoursesService();
    }

    @Override
    public boolean createCourse(Course course) {
        return courseOperations.createCourse(course);
    }

    @Override
    public List<Course> getAllCourses() { return courseOperations.getAllCourses();}

    @Override
    public boolean deleteCourse(int id) {
        Course course = courseOperations.getCourseById(id);
        if (course == null) {
            return false;
        }
        List<Student> students = course.getStudents();
        for (Student student : students) {
            deleteStudentFromCourse(student, course);
        }
        return courseOperations.deleteCourse(id);
    }

    @Override
    public Course getCourseById(int id) {return courseOperations.getCourseById(id);}

    @Override
    public boolean updateCourse(Course course) {
        if (courseOperations.updateCourse(course)) {
            List<Student> students = course.getStudents();
            for (Student student : students) {
                updateCourseToStudent(student, course);
            }
            return true;
        }
        return false;
    }

    @Override
    public List<Course> getCourseByCategory(Category category) {return courseOperations.getCourseByCategory(category);}

    @Override
    public List<Student> getAllStudents() { return studentOperations.getAllStudents(); }

    @Override
    public boolean createStudent(Student s) {
        return false;
    }

    @Override
    public List<Student> getStudentByGroup(int group) {
        return studentOperations.getStudentByGroup(group);
    }

    @Override
    public Student getStudentById(int id) {
        return studentOperations.getStudentById(id);
    }


    @Override
    public boolean updateStudent(Student student) {
        if (studentOperations.updateStudent(student)) {
            List<Course> courses = student.getCourses();
            for (Course c : courses) {
                updateStudentToCourse(student, c);
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteStudent(int id) {
        Student student = studentOperations.getStudentById(id);
        if (student == null) {
            return false;
        }
        List<Course> courses = student.getCourses();
        for (Course c : courses) {
            deleteStudentFromCourse(student, c);
        }
        return studentOperations.deleteStudent(id);
    }

    @Override
    public boolean addStudentOnCourse(Student student, Course course) {
        if (student != null && course != null) {
            if (!isStudentOnCourse(student, course)) {
                student.getCourses().add(course);
                course.getStudents().add(student);
                studentOperations.updateStudent(student);
                courseOperations.updateCourse(course);
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public boolean deleteStudentFromCourse(Student student, Course course) {
        if (student != null && course != null) {
            if (isStudentOnCourse(student, course)) {
                student.getCourses().remove(course);
                course.getStudents().remove(student);
                studentOperations.updateStudent(student);
                courseOperations.updateCourse(course);
                return true;
            }
            return false;
        }
        return false;
    }


    private boolean updateCourseToStudent(Student student, Course course) {
        List<Course> courses = student.getCourses();
        for (Course c : courses) {
            if (c.getId() == course.getId()) {
                student.getCourses().remove(c);
                student.getCourses().add(course);
                return true;
            }
        }
        return false;
    }


    private boolean updateStudentToCourse(Student student, Course course) {
        List<Student> students = course.getStudents();
        for (Student s : students) {
            if (s.getId() == student.getId()) {
                course.getStudents().remove(s);
                course.getStudents().add(student);
                return true;
            }
        }
        return false;
    }

    private boolean isStudentOnCourse(Student student, Course course) {
        List<Student> students = course.getStudents();
        for (Student item : students) {
            if (item.getId() == student.getId()) {
                return true;
            }
        }
        return false;
    }

}