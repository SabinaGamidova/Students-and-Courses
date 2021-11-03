package controller;
import model.Category;
import model.Course;
import model.Student;
import service.MainService;
import service.StudentService;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
        System.out.println("Выберите опцию: 1-Crud для студента, 2-Crud для курса, 3-Записать студента на курс, " +
                "4-Удалить студента с курса," + " 0 - выход");
        choose = Integer.parseInt(scanner.nextLine());
        do {
            switch (choose) {
                case 1:
                    do {
                        System.out.println("Выберите, что вы хотите сделать: 1-Добавить нового ученика; 2-Получить список всех, 3-Удалить ученика, " +
                                "4-Сортировать учеников по курсу, 5-Сортировать учеников по группе," +
                                "6-Найти ученика по id" + " 7-Обновить данные об ученике" + " 0 - выход");
                        choose = Integer.parseInt(scanner.nextLine());
                        switch (choose) {
                            case 1 -> createStudent();
                            case 2 -> getAll();
                            case 3 -> delete();
                            case 5 -> getByGroup();
                            case 6 -> getById();
                            case 7 -> update();
                            case 0 -> System.exit(1);
                            default -> System.out.println("Операция не поддерживается.");
                        }
                        System.out.println("Хотите продолжить? 1-да, другие числа-нет");
                        choose = Integer.parseInt(scanner.nextLine());

                    } while (choose == 1);
                    break;
                case 2:
                    System.out.println("Выберите, что вы хотите сделать: 1-Добавить новый курс; 2-Получить список всех курсов, 3-Удалить курс, " +
                            "4-Получить курс по категории" + "5-Найти курс по id" + " 6-Обновить данные о курсе," + " 0 - выход");
                    choose = Integer.parseInt(scanner.nextLine());
                    do {
                        System.out.println("Выберите, что вы хотите сделать: 1-Добавить новый курс; 2-Получить список всех курсов, 3-Удалить курс, " +
                                "4-Найти курс по категории, 5-Найти курс по id," +
                                " 6-Обновить данные о курсе" + "0 - выход");
                        choose = Integer.parseInt(scanner.nextLine());
                        switch (choose) {
                            case 1 -> createCourse();
                            case 2 -> getAllCourses();
                            case 3 -> deleteCourse();
                            case 5 -> getByCategory();
                            case 6 -> getCourseById();
                            case 7 -> updateCourse();
                            case 0 -> System.exit(1);
                            default -> System.out.println("Операция не поддерживается.");
                        }
                        System.out.println("Хотите продолжить? 1-да, другие числа-нет");
                        choose = Integer.parseInt(scanner.nextLine());

                    } while (choose == 1);
                    break;
                case 3:
                    addStudentOnCourse();
                    break;
                case 4:
                    deleteStudentFromCourse();
                    break;
                case 0: {
                    System.exit(1);
                }
                default: {
                    System.out.println("Неизвестная операция!");
                    break;
                }
            }
        }while(choose!=0);
    }

    private void createStudent(){
        System.out.println("Введите имя:");
        String firstname = scanner.nextLine();
        System.out.println("Введите фамилию:");
        String surname = scanner.nextLine();
        System.out.println("Введите номер курса:");
        Integer course = Integer.parseInt(scanner.nextLine());
        System.out.println("Введите номер группы:");
        Integer group = Integer.parseInt(scanner.nextLine());
        Student student = new Student(firstname, surname,group);
        if(mainService.createStudent(student)){
            System.out.println("Данные про ученика успешно созданы.");
            return;
        }
        System.out.println("Ошибка при сохранении данных об ученике.");
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
        if(mainService.deleteStudent(id)){
            System.out.println("Ученик успешно удалён.");
            return;
        }
        System.out.println("Ученик не удалён.");
    }


    private void getByGroup() {
        getAll();
        System.out.println("Чтобы получить список учеников определенной группы, сначала введите номер группы:");
        int group = Integer.parseInt(scanner.nextLine());
        if(mainService.getStudentByGroup(group) != null){
            for (Student student : mainService.getStudentByGroup(group)) {
                System.out.println(student.toString());

            }
            return;
        }
        System.out.println("Вы ввели неправильный номер группы.");
    }

    private void getById() {
        System.out.println("Введите id ученика, которого хотите найти:");
        int id = Integer.parseInt(scanner.nextLine());
        if(mainService.getStudentById(id) != null){
            System.out.println(mainService.getStudentById(id).toString());
            return;
        }
        System.out.println("Вы ввели неправильный id.");
    }


    private void update() {
        getAll();
        System.out.println("Введите id студента, которого хотите изменить:");
        int id = Integer.parseInt(scanner.nextLine());
        Student student = mainService.getStudentById(id);
        if(student == null) {
            System.out.println("Неправильный id");
            return;
        }
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
        if(mainService.updateStudent(student)){
            System.out.println("Обновление прошло успешно. " + student.toString());
            return;
        }
        System.out.println("Обновление неуспешно.");
    }


    private void createCourse(){
        System.out.println("Введите название:");
        String name = scanner.nextLine();
        System.out.println("Введите категорию: 1)Математика, 2)Английский, 3)Физика, 4)Биология");
        Integer category = Integer.parseInt(scanner.nextLine());
        System.out.println("Введите данные о преподавателе:");
        String teacher = scanner.nextLine();
        Course course = new Course(name, chooseCategory(category), teacher);
        if(mainService.createCourse(course)){
            System.out.println("Данные про курс успешно созданы.");
            return;
        }
        System.out.println("Ошибка при сохранении данных о курсе.");
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
        if(mainService.deleteCourse(id)){
            System.out.println("Курс успешно удалён.");
            return;
        }
        System.out.println("Курс не удалён.");
    }



    private void getByCategory() {
        System.out.println("Введите категорию курса, который хотите найти:");
        Integer category = Integer.parseInt(scanner.nextLine());
        if(mainService.getCourseByCategory(chooseCategory(category)) != null){
            System.out.println(mainService.getCourseByCategory(chooseCategory(category)).toString());
            return;
        }
        System.out.println("Вы ввели неправильную категорию.");
    }

    private void getCourseById() {
        System.out.println("Введите id курса, который хотите найти:");
        int id = Integer.parseInt(scanner.nextLine());
        if(mainService.getCourseById(id) != null){
            System.out.println(mainService.getCourseById(id).toString());
            return;
        }
        System.out.println("Вы ввели неправильный id.");
    }


    private void updateCourse() {
        getAllCourses();
        System.out.println("Введите id курса, который хотите изменить:");
        int id = Integer.parseInt(scanner.nextLine());
        Course course = mainService.getCourseById(id);
        if(course == null) {
            System.out.println("Неправильный id");
            return;
        }
        System.out.println("Что хотите изменить? 1 - Название курса, 2 - Категория курса, 3 - Имя преподавателя");
        int choose = Integer.parseInt(scanner.nextLine());
        switch(choose) {
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
            case 3-> {
                System.out.println("Введите новое имя преподавателя:");
                String teacher = scanner.nextLine();
                course.setTeacher(teacher);
            }
            default-> {
                System.out.println("Неизвестная операция.");
                return;
            }
        }
        if(mainService.updateCourse(course)){
            System.out.println("Обновление прошло успешно. " + course.toString());
            return;
        }
        System.out.println("Обновление неуспешно.");
    }


    public void addStudentOnCourse() {
        Course course = new Course();
        Student student = new Student();
        System.out.println("Введите id студента:");
        int studentId = Integer.parseInt(scanner.nextLine());
        System.out.println("Введите id курса:");
        int courseId = Integer.parseInt(scanner.nextLine());
        course = mainService.getCourseById(courseId);
        student = mainService.getStudentById(studentId);
        if(mainService.addStudentOnCourse(student, course)){
            System.out.println("Студент успешно добавлен на этот курс");
            return;
        }
        System.out.println("Студент не добавлен на этот курс");
    }

    public void deleteStudentFromCourse() {
        Course course = new Course();
        Student student = new Student();
        System.out.println("Введите id студента:");
        int studentId = Integer.parseInt(scanner.nextLine());
        System.out.println("Введите id курса:");
        int courseId = Integer.parseInt(scanner.nextLine());
        course = mainService.getCourseById(courseId);
        student = mainService.getStudentById(studentId);
        if(mainService.deleteStudentFromCourse(student, course)){
            System.out.println("Студент успешно удален с курса");
            return;
        }
        System.out.println("Студент не удален с курса");
    }



}
