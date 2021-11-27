package exceptions;

import model.Category;

public class CategoryException extends Exception{
    private String message;


    @Override
    public String getMessage() {
        return message;
    }
    public CategoryException(String message) {
        this.message = message;
    }

}
