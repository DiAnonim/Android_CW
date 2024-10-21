package kz.example.lesson_3.Lesson14.apiService;

import java.util.List;

import kz.example.lesson_3.Lesson14.model.Post;
import kz.example.lesson_3.Lesson14.model.Todo;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("/posts")
    Call<List<Post>> getPosts();

    @GET("/todos")
    Call<List<Todo>> getTodos();
}
