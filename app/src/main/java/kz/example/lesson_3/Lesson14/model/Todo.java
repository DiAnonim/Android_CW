package kz.example.lesson_3.Lesson14.model;

public class Todo {
    private int userId;
    private int todoId;
    private String title;
    private Boolean completed;

    public int getUserId() {
        return userId;
    }

    public int getTodoId() {
        return todoId;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public String getTitle() {
        return title;
    }
}
