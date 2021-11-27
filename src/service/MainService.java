package service;

import exceptions.CategoryException;
import exceptions.CourseException;
import exceptions.GroupException;
import exceptions.StudentException;
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
    public Course createCourse(Course course) throws CourseException, CategoryException{
        return courseOperations.createCourse(course);
    }

    @Override
    public List<Course> getAllCourses() { return courseOperations.getAllCourses();}

    @Override
    public void deleteCourse(int id) throws CourseException, StudentException, GroupException, CategoryException{
        Course course = courseOperations.getCourseById(id);
        List<Student> students = course.getStudents();
        for (Student student : students) {
            deleteStudentFromCourse(student.getId(), course.getId());
        }
        courseOperations.deleteCourse(id);
    }

    @Override
    public Course getCourseById(int id) throws CourseException {return courseOperations.getCourseById(id);}

    @Override
    public void updateCourse(Course course) throws CourseException, CategoryException{
        courseOperations.updateCourse(course);
        List<Student> students = course.getStudents();
        for (Student student : students) {
            updateCourseToStudent(student, course);
        }
    }

    @Override
    public List<Course> getCourseByCategory(Category category) throws CategoryException {return courseOperations.getCourseByCategory(category);}

    @Override
    public List<Student> getAllStudents() { return studentOperations.getAllStudents(); }

    @Override
    public Student createStudent(Student s) throws StudentException, GroupException{return studentOperations.createStudent(s);}

    @Override
    public List<Student> getStudentByGroup(int group) throws GroupException {
        return studentOperations.getStudentByGroup(group);
    }

    @Override
    public Student getStudentById(int id) throws StudentException {
        return studentOperations.getStudentById(id);
    }


    @Override
    public void updateStudent(Student student) throws StudentException, GroupException{
        studentOperations.updateStudent(student);
        List<Course> courses = student.getCourses();
        for (Course c : courses) {
            updateStudentToCourse(student, c);
        }
    }

    @Override
    public void deleteStudent(int id) throws StudentException, CourseException, GroupException, CategoryException{
        Student student = studentOperations.getStudentById(id);
        List<Course> courses = student.getCourses();
        for (Course c : courses) {
            deleteStudentFromCourse(student.getId(), c.getId());
        }
        studentOperations.deleteStudent(id);
    }

    @Override
    public void addStudentOnCourse(int studentId, int courseId) throws StudentException, CourseException, GroupException, CategoryException{
        Student student = getStudentById(studentId);
        Course course = getCourseById(courseId);
        if (student != null && course != null) {
            if (!isStudentOnCourse(student, course)) {
                student.getCourses().add(course);
                course.getStudents().add(student);
                studentOperations.updateStudent(student);
                courseOperations.updateCourse(course);
                return;
            }
            //?
        }
        if(student == null){
            throw new StudentException("Нельзя добавить студента " + student.getFirstname() +" на курс " + course.getName());
        }
        throw new CourseException("Нельзя добавить на курс " + course.getName() +" студента " + student.getFirstname());
    }


    @Override
    public void deleteStudentFromCourse(int studentId, int courseId) throws CourseException, StudentException, GroupException, CategoryException{
        Student student = getStudentById(studentId);
        Course course = getCourseById(courseId);
        if (student != null && course != null) {
            if (isStudentOnCourse(student, course)) {
                student.getCourses().remove(course);
                course.getStudents().remove(student);
                studentOperations.updateStudent(student);
                courseOperations.updateCourse(course);
                return;
            }
        }
        if(student == null){
            throw new StudentException("Нельзя удалить студента с курса ");
        }
        throw new CourseException("Нельзя удалить курс у студента.");
    }


    @Override
    public List<Course> getAllCoursesOfStudent(int studentId) throws StudentException{
            return studentOperations.getAllCoursesOfStudent(studentId);
    }

    @Override
    public List<Student> getAllStudentsOnCourse(int courseId) throws CourseException{
        return courseOperations.getAllStudentsOnCourse(courseId);
    }


//check
    private void updateCourseToStudent(Student student, Course course) throws CourseException{
        List<Course> courses = student.getCourses();
        for (Course c : courses) {
            if (c.getId() == course.getId()) {
                student.getCourses().remove(c);
                student.getCourses().add(course);
                return;
            }
        }
        throw new CourseException("Нельзя обновить курс " + course.getName() +" у студента " + student.getFirstname());
    }


    //check
    private void updateStudentToCourse(Student student, Course course) throws StudentException{
        List<Student> students = course.getStudents();
        for (Student s : students) {
            if (s.getId() == student.getId()) {
                course.getStudents().remove(s);
                course.getStudents().add(student);
                return;
            }
        }
        throw new StudentException("Нельзя обновить студента " + student.getFirstname() +" на курсе " + course.getName());
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