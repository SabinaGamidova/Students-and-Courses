package exceptions;

public class GroupException extends Exception{
    private int group;
    private String message;

    public int getGroup() {
        return group;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public GroupException(int group, String message) {
        this.group = group;
        this.message = message;
    }

    public GroupException(String message) {
        this.message = message;
    }
}
