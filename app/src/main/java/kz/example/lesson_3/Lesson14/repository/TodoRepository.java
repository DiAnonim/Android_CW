package kz.example.lesson_3.Lesson14.repository;

import java.util.List;

import kz.example.lesson_3.Lesson14.apiService.ApiService;
import kz.example.lesson_3.Lesson14.model.Todo;
import retrofit2.Call;

public class TodoRepository {
    private final ApiService apiService;

    public TodoRepository(ApiService apiService) {
        this.apiService = apiService;
    }

    public Call<List<Todo>> getTodos(){
        return apiService.getTodos();
    }
}
