package model;

public enum Category {
    MATH ("Математика"),
    ENGLISH ("Английский"),
    PHYSICS ("Физика"),
    BIOLOGY ("Биология");
    private String description;

    Category(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
