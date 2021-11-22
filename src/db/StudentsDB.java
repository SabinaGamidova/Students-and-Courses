package db;
import model.Course;
import model.Student;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class StudentsDB {
        private List<Student>students;

public StudentsDB() {
    students = new ArrayList<>();
}

    public List<Student> getAllStudents(){
        return students;
    }


    public Student createStudent(Student s){
        s.setId(generateId((int) (Math.random() * 10000000)));
        students.add(s);
        return s;
    }

    public void deleteStudent(int id){
        for (Student s:students) {
            if(s.getId() == id){
                students.remove(s);
                return;
            }
        }
        throw new RuntimeException("Студента с таким id не существует.");
    }

    public List<Student> getStudentByGroup(int group) {
        List<Student>pupils = new ArrayList<>();
        for (Student s:students) {
            if(s.getGroup() == group){
                pupils.add(s);
            }
        }
        return pupils;
    }

    public Student getStudentById(int id) {
        for (Student s:students) {
            if(s.getId() == id){
                return s;
            }
        }
        throw new RuntimeException("Студента с таким id не существует.");
    }


    public void updateStudent(Student student) {
        for (Student s:students) {
            if(s.getId() == student.getId()){
                //1 способ
                students.remove(s);
                students.add(student);
                return;
                //2 способ
                /*s.setFirstname(student.getFirstname());
                s.setSurname(student.getSurname());
                s.setCourse(student.getCourse());
                s.setGroup(student.getGroup());*/
            }

        }
        throw new RuntimeException("Студента с таким id не существует.");
    }

    private int generateId(int id){
        for (Student s: students) {
            if(s.getId() == id){
                return generateId((int) (Math.random() * 10000000));
            }
        }
        return id;
    }

}
