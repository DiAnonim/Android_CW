package kz.example.lesson_3.Lesson14.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import kz.example.lesson_3.Lesson14.model.Todo;
import kz.example.lesson_3.Lesson14.repository.TodoRepository;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TodoViewModel extends ViewModel {

    private final TodoRepository todoRepository;
    private final MutableLiveData<List<Todo>> todosLiveData = new MutableLiveData<>();

    public TodoViewModel(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public LiveData<List<Todo>> getTodosLiveData() {
        return todosLiveData;
    }

    public void fetchTodos() {
        todoRepository.getTodos().enqueue(new Callback<List<Todo>>() {
            @Override
            public void onResponse(Call<List<Todo>> call, Response<List<Todo>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    todosLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Todo>> call, Throwable t) {

            }
        });
    }
}
