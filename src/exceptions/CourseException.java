package exceptions;

public class CourseException extends Exception{
    private int id;
    private String name;
    private String message;

    public CourseException(int id, String message) {
        this.id = id;
        this.message = message;
    }

    public CourseException(int id, String name, String message) {
        this.id = id;
        this.name = name;
        this.message = message;
    }


    public CourseException(String message) {
        this.message = message;
    }



    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
