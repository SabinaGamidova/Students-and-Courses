package exceptions;

public class StudentException extends Exception{
    private int id;
    private String firstname;
    private String lastname;
    private String message;

    public StudentException(int id, String firstname, String lastname, String message) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.message = message;
    }

    public StudentException(String message) {
        this.message = message;
    }

    public StudentException(int id, String message) {
        this.id = id;
        this.message = message;
    }

    public StudentException(int id, String firstname, String message) {
        this.id = id;
        this.firstname = firstname;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
