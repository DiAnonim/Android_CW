package kz.example.lesson_3.Lesson14.repository;

import java.util.List;

import kz.example.lesson_3.Lesson14.apiService.ApiService;
import kz.example.lesson_3.Lesson14.model.Post;
import retrofit2.Call;

public class PostRepository {
    private final ApiService apiService;

    public PostRepository(ApiService apiService) {
        this.apiService = apiService;
    }

    public Call<List<Post>> getPosts(){
        return apiService.getPosts();
    }
}
