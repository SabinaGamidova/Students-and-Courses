package controller;
import exceptions.CategoryException;
import exceptions.CourseException;
import exceptions.GroupException;
import exceptions.StudentException;
import model.Category;
import model.Course;
import model.Student;
import service.*;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

import static model.Category.*;

public class Controller {
    private Scanner scanner;
    private MainService mainService;



    public Controller() {
        scanner = new Scanner(System.in);
        mainService = new MainService();
    }

    public void userInterface(){
        int choose;
        do {
            System.out.println("Выберите опцию: 1-Crud для студента, 2-Crud для курса, 3-Записать студента на курс, " +
                    "4-Удалить студента с курса," + " 0 - выход");
            choose = Integer.parseInt(scanner.nextLine());
            switch (choose) {
                case 1 -> studentCrudInterface();
                case 2 -> courseCrudInterface();
                case 3 -> addStudentOnCourse();
                case 4 -> deleteStudentFromCourse();
                case 0 -> System.exit(1);
                default -> System.out.println("Неизвестная операция!");
                }
        }while(choose!=0);
    }

    private void courseCrudInterface() {
        int choose;
        do {
            System.out.println("Выберите, что вы хотите сделать: 1-Добавить новый курс; 2-Получить список всех курсов, 3-Удалить курс, " +
                    "4-Найти курс по категории, 5-Найти курс по id," +
                    " 6-Обновить данные о курсе, 7-Получить всех студентов на курсе, 0 - выход");
            choose = Integer.parseInt(scanner.nextLine());
            switch (choose) {
                case 1 -> createCourse();
                case 2 -> getAllCourses();
                case 3 -> deleteCourse();
                case 4 -> getByCategory();
                case 5 -> getCourseById();
                case 6 -> updateCourse();
                case 7 -> getAllStudentsOnCourse();
                case 0 -> System.exit(1);
                default -> System.out.println("Операция не поддерживается.");
            }
            System.out.println("Хотите продолжить? 1-да, другие числа-нет");
            choose = Integer.parseInt(scanner.nextLine());

        } while (choose == 1);
    }



    private void studentCrudInterface(){
        int choose;
        do {
            System.out.println("Выберите, что вы хотите сделать: 1-Добавить нового ученика; 2-Получить список всех, 3-Удалить ученика, " +
                    "4-Сортировать учеников по группе, 5-Найти ученика по id, 6-Обновить данные об ученике, 7-Получить все курсы студента, 0 - выход");
            choose = Integer.parseInt(scanner.nextLine());
            switch (choose) {
                case 1 -> createStudent();
                case 2 -> getAll();
                case 3 -> delete();
                case 4 -> getByGroup();
                case 5 -> getById();
                case 6 -> update();
                case 7 -> getAllCoursesOfStudent();
                case 0 -> System.exit(1);
                default -> System.out.println("Операция не поддерживается.");
            }
            System.out.println("Хотите продолжить? 1-да, другие числа-нет");
            choose = Integer.parseInt(scanner.nextLine());

        } while (choose == 1);
    }


    private void createStudent(){
        System.out.println("Введите имя:");
        String firstname = scanner.nextLine();
        System.out.println("Введите фамилию:");
        String surname = scanner.nextLine();
        System.out.println("Введите номер группы:");
        int group = Integer.parseInt(scanner.nextLine());
        Student student = new Student(firstname, surname, group);
        try{
            student = mainService.createStudent(student);
            System.out.println("Данные про ученика успешно созданы.");
            System.out.println(student.toString());
        }
        catch(StudentException studentException){
            System.out.println(studentException.getMessage());
        }
        catch(GroupException groupException){
            System.out.println(groupException.getMessage());
        }
    }


    private void getAll() {
        List<Student>students = mainService.getAllStudents();
        for (Student s : students) {
            System.out.println(s.toString());
        }
    }

    private void delete() {
        getAll();
        System.out.println("Введите id ученика, которого хотите удалить:");
        int id = Integer.parseInt(scanner.nextLine());
        try{
            mainService.deleteStudent(id);
            System.out.println("Ученик успешно удалён.");
        }
        catch(StudentException studentException){
            System.out.println(studentException.getMessage());
        }
        catch(CourseException courseException){
            System.out.println(courseException.getMessage());
        }
        catch(GroupException groupException){
            System.out.println(groupException.getMessage());
        }
        catch(CategoryException categoryException){
            System.out.println(categoryException.getMessage());
        }
    }

    private void getByGroup() {
        getAll();
        System.out.println("Чтобы получить список учеников определенной группы, сначала введите номер группы:");
        int group = Integer.parseInt(scanner.nextLine());
        try{
            mainService.getStudentByGroup(group);
            for (Student student : mainService.getStudentByGroup(group)) {
                System.out.println(student.toString());

            }
        }
        catch(GroupException groupException){
            System.out.println(groupException.getMessage());
        }
    }

    private void getById() {
        try{
            System.out.println("Введите id ученика, которого хотите найти:");
            int id = Integer.parseInt(scanner.nextLine());
            Student student = mainService.getStudentById(id);
            System.out.println(student.toString());
        }
        catch(StudentException studentException){
            System.out.println(studentException.getMessage());
        }
    }


    private void update() {
        try {
            getAll();
            System.out.println("Введите id студента, которого хотите изменить:");
            int id = Integer.parseInt(scanner.nextLine());
            Student student = mainService.getStudentById(id);
            System.out.println("Что хотите изменить? 1 - Имя, 2 - Фамилию, 3 - Курс, 4 - Группу.");
            int choose = Integer.parseInt(scanner.nextLine());
            switch(choose) {
                case 1 -> {
                    System.out.println("Введите новое имя ученика:");
                    String firstname = scanner.nextLine();
                    student.setFirstname(firstname);
                }
                case 2 -> {
                    System.out.println("Введите новую фамилию ученика:");
                    String surname = scanner.nextLine();
                    student.setSurname(surname);
                }
                case 4-> {
                    System.out.println("Введите новый номер группы ученика:");
                    int group = Integer.parseInt(scanner.nextLine());
                    student.setGroup(group);
                }
                default-> {
                    System.out.println("Неизвестная операция.");
                    return;
                }
            }
            mainService.updateStudent(student);
            System.out.println("Обновление прошло успешно. " + student.toString());
        }
        catch(StudentException studentException){
            System.out.println(studentException.getMessage());
        }
        catch(GroupException groupException){
            System.out.println(groupException.getMessage());
        }
    }


    private void createCourse(){
        try{
            System.out.println("Введите название:");
            String name = scanner.nextLine();
            System.out.println("Введите категорию: 1)Математика, 2)Английский, 3)Физика, 4)Биология");
            Integer category = Integer.parseInt(scanner.nextLine());
            System.out.println("Введите данные о преподавателе:");
            String teacher = scanner.nextLine();
            Course course = new Course(name, chooseCategory(category), teacher);
            mainService.createCourse(course);
            System.out.println("Данные про курс успешно созданы.");
        }
        catch(CourseException courseException){
            System.out.println(courseException.getMessage());
        }
        catch(CategoryException categoryException){
            System.out.println(categoryException.getMessage());
        }
    }

    private Category chooseCategory(Integer num){
            switch(num){
                case 1 -> {
                    return MATH;
                }
                case 2 -> {
                    return ENGLISH;
                }
                case 3 -> {
                    return PHYSICS;
                }
                case 4 -> {
                    return BIOLOGY;
                }
                default -> {System.out.println("Неизвестная операция!"); System.exit(1);}
            }
            return null;
    }


    private void getAllCourses() {
        List<Course>courses = mainService.getAllCourses();
        for (Course c : courses) {
            System.out.println(c.toString());
        }
    }


    private void deleteCourse() {
        getAllCourses();
        System.out.println("Введите id курса, который хотите удалить:");
        int id = Integer.parseInt(scanner.nextLine());
        try{
            mainService.deleteCourse(id);
            System.out.println("Курс успешно удалён.");
        }
        catch(CourseException courseException){
            System.out.println(courseException.getMessage());
        }
        catch(StudentException studentException){
            System.out.println(studentException.getMessage());
        }
        catch(GroupException groupException){
            System.out.println(groupException.getMessage());
        }
        catch(CategoryException categoryException){
            System.out.println(categoryException.getMessage());
        }
    }



    private void getByCategory() {
        try {
            System.out.println("Введите категорию курса, который хотите найти: 1 - Математика, 2 - Английский, 3 - Физика, 4 - Биология.");
            Integer category = Integer.parseInt(scanner.nextLine());
            List<Course>courses = mainService.getCourseByCategory(chooseCategory(category));
            courses.forEach(System.out::println);
            }
        catch(CategoryException categoryException){
            System.out.println(categoryException.getMessage());
        }
    }


    private void getCourseById() {
        try{
            System.out.println("Введите id курса, который хотите найти:");
            int id = Integer.parseInt(scanner.nextLine());
            Course course = mainService.getCourseById(id);
            System.out.println(course.toString());
        }
        catch(CourseException courseException){
            System.out.println(courseException.getMessage());
        }
    }


    private void updateCourse() {
        try{
        getAllCourses();
        System.out.println("Введите id курса, который хотите изменить:");
        int id = Integer.parseInt(scanner.nextLine());
        Course course = mainService.getCourseById(id);
        if (course == null) {
            System.out.println("Неправильный id");
            return;
        }
        System.out.println("Что хотите изменить? 1 - Название курса, 2 - Категория курса, 3 - Имя преподавателя");
        int choose = Integer.parseInt(scanner.nextLine());
        switch (choose) {
            case 1 -> {
                System.out.println("Введите новое название курса:");
                String name = scanner.nextLine();
                course.setName(name);
            }
            case 2 -> {
                System.out.println("Введите новую категорию курса:");
                Integer category = Integer.parseInt(scanner.nextLine());
                course.setCategory(chooseCategory(category));
            }
            case 3 -> {
                System.out.println("Введите новое имя преподавателя:");
                String teacher = scanner.nextLine();
                course.setTeacher(teacher);
            }
            default -> {
                System.out.println("Неизвестная операция.");
                return;
            }
        }
        mainService.updateCourse(course);
        System.out.println("Обновление прошло успешно. " + course.toString());
        }
        catch(CourseException courseException){
            System.out.println(courseException.getMessage());
        }
        catch(CategoryException categoryException){
            System.out.println(categoryException.getMessage());
        }

    }


    public void addStudentOnCourse() {
        try {
            System.out.println("Введите id студента:");
            int studentId = Integer.parseInt(scanner.nextLine());
            System.out.println("Введите id курса:");
            int courseId = Integer.parseInt(scanner.nextLine());
            mainService.addStudentOnCourse(studentId, courseId);
            System.out.println("Студент успешно добавлен(а) на курс.");
        }
        catch(CourseException courseException){
            System.out.println(courseException.getMessage());
        }
        catch(StudentException studentException){
            System.out.println(studentException.getMessage());
        }
        catch(GroupException groupException){
            System.out.println(groupException.getMessage());
        }
        catch(CategoryException categoryException){
            System.out.println(categoryException.getMessage());
        }
    }


    public void deleteStudentFromCourse(){
        try {
            System.out.println("Введите id студента:");
            int studentId = Integer.parseInt(scanner.nextLine());
            System.out.println("Введите id курса:");
            int courseId = Integer.parseInt(scanner.nextLine());
            mainService.deleteStudentFromCourse(studentId, courseId);
            System.out.println("Студент успешно удален(а) с курса.");
        }
        catch(CourseException courseException){
            System.out.println(courseException.getMessage());
        }
        catch(StudentException studentException){
            System.out.println(studentException.getMessage());
        }
        catch(GroupException groupException){
            System.out.println(groupException.getMessage());
        }
        catch(CategoryException categoryException){
            System.out.println(categoryException.getMessage());
        }
    }


    public void getAllStudentsOnCourse(){
        try{
            System.out.println("Введите id курса, студентов которого хотите получить:");
            int courseId = Integer.parseInt(scanner.nextLine());
            List<Student>students = mainService.getAllStudentsOnCourse(courseId);
                students.forEach(System.out::println);
        }
        catch(Exception exception){
            System.out.println(exception.getMessage());
        }
    }

    public void getAllCoursesOfStudent(){
        try{
            System.out.println("Введите id студента, курсы которого хотите получить:");
            int studentId = Integer.parseInt(scanner.nextLine());
            List<Course>courses = mainService.getAllCoursesOfStudent(studentId);
            courses.forEach(System.out::println);
        }
        catch(Exception exception){
            System.out.println(exception.getMessage());
        }
    }



}
