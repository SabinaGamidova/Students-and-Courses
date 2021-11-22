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
    public Course createCourse(Course course) {
        return courseOperations.createCourse(course);
    }

    @Override
    public List<Course> getAllCourses() { return courseOperations.getAllCourses();}

    @Override
    public void deleteCourse(int id) {
        Course course = courseOperations.getCourseById(id);
        List<Student> students = course.getStudents();
        for (Student student : students) {
            deleteStudentFromCourse(student.getId(), course.getId());
        }
        courseOperations.deleteCourse(id);
    }

    @Override
    public Course getCourseById(int id) {return courseOperations.getCourseById(id);}

    @Override
    public void updateCourse(Course course) {
        courseOperations.updateCourse(course);
        List<Student> students = course.getStudents();
        for (Student student : students) {
            updateCourseToStudent(student, course);
        }
    }

    @Override
    public List<Course> getCourseByCategory(Category category) {return courseOperations.getCourseByCategory(category);}

    @Override
    public List<Student> getAllStudents() { return studentOperations.getAllStudents(); }

    @Override
    public Student createStudent(Student s) {return studentOperations.createStudent(s);}

    @Override
    public List<Student> getStudentByGroup(int group) {
        return studentOperations.getStudentByGroup(group);
    }

    @Override
    public Student getStudentById(int id) {
        return studentOperations.getStudentById(id);
    }


    @Override
    public void updateStudent(Student student) {
        studentOperations.updateStudent(student);
        List<Course> courses = student.getCourses();
        for (Course c : courses) {
            updateStudentToCourse(student, c);
        }
    }

    @Override
    public void deleteStudent(int id) {
        Student student = studentOperations.getStudentById(id);
        List<Course> courses = student.getCourses();
        for (Course c : courses) {
            deleteStudentFromCourse(student.getId(), c.getId());
        }
        studentOperations.deleteStudent(id);
    }

    @Override
    public boolean addStudentOnCourse(int studentId, int courseId) {
        Student student = getStudentById(studentId);
        Course course = getCourseById(courseId);
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
        /*
        Сделать void
        try{
        Student student = getStudentById(studentId);
        Course course = getCourseById(courseId);
        isStudentOnCourse(student, course);
        student.getCourses().add(course);
        course.getStudents().add(student);
        studentOperations.updateStudent(student);
        courseOperations.updateCourse(course);
         }
        throw new RuntimeException("Нельзя добавить студента на курс.");
        */
    }

    @Override
    public boolean deleteStudentFromCourse(int studentId, int courseId) {
        Student student = getStudentById(studentId);
        Course course = getCourseById(courseId);
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

        /*
        Сделать void
        try{
        Student student = getStudentById(studentId);
        Course course = getCourseById(courseId);
        isStudentOnCourse(student, course);
        student.getCourses().remove(course);
        course.getStudents().remove(student);
        studentOperations.updateStudent(student);
        courseOperations.updateCourse(course);
         }
        throw new RuntimeException("Нельзя удалить студента с курса.");
        */
    }

    @Override
    public List<Course> getAllCoursesOfStudent(int studentId){
            return studentOperations.getAllCoursesOfStudent(studentId);
    }

    @Override
    public List<Student> getAllStudentsOnCourse(int courseId){
        return courseOperations.getAllStudentsOnCourse(courseId);
    }


//check
    private void updateCourseToStudent(Student student, Course course) {
        List<Course> courses = student.getCourses();
        for (Course c : courses) {
            if (c.getId() == course.getId()) {
                student.getCourses().remove(c);
                student.getCourses().add(course);
                return;
            }
        }
        throw new RuntimeException("Нельзя обновить курс у студента.");
    }


    //check
    private void updateStudentToCourse(Student student, Course course) {
        List<Student> students = course.getStudents();
        for (Student s : students) {
            if (s.getId() == student.getId()) {
                course.getStudents().remove(s);
                course.getStudents().add(student);
                return;
            }
        }
        throw new RuntimeException("Нельзя обновить студента на курсе.");
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