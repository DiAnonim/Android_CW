package kz.example.lesson_3.Lesson14.view;

import android.os.Bundle;
import android.widget.ProgressBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import kz.example.lesson_3.Lesson12.dao.AppDatabase;
import kz.example.lesson_3.Lesson14.apiService.ApiService;
import kz.example.lesson_3.Lesson14.koinModule.AppModule;
import kz.example.lesson_3.Lesson14.koinModule.PostAdapter;
import kz.example.lesson_3.Lesson14.koinModule.TodoAdapter;
import kz.example.lesson_3.Lesson14.model.Post;
import kz.example.lesson_3.Lesson14.repository.PostRepository;
import kz.example.lesson_3.Lesson14.repository.TodoRepository;
import kz.example.lesson_3.Lesson14.viewModel.PostViewModel;
import kz.example.lesson_3.Lesson14.viewModel.TodoViewModel;
import kz.example.lesson_3.R;
import retrofit2.Retrofit;

public class MainActivity_Lesson14 extends AppCompatActivity {

    private TodoViewModel todoViewModel;
    private TodoAdapter todoAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_lesson14);
        EdgeToEdge.enable(this);


        RecyclerView recyclerView = findViewById(R.id.recyclerView1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        todoAdapter = new TodoAdapter(new ArrayList<>());
        recyclerView.setAdapter(todoAdapter);

        ApiService apiService = AppModule.getRetrofitInstance().create(ApiService.class);
        TodoRepository todoRepository = new TodoRepository(apiService);

        todoViewModel = new ViewModelProvider(this,
                new ViewModelProvider.Factory() {
                    @Override
                    public <T extends ViewModel> T create(Class<T> modelClass) {
                        if (modelClass.isAssignableFrom(TodoViewModel.class)) {
                            return (T) new TodoViewModel(todoRepository);
                        }
                        throw new IllegalArgumentException("Unknown ViewModel class");
                    }
                }
        ).get(TodoViewModel.class);

        todoViewModel.getTodosLiveData().observe(this, todos -> {
            if (todos != null) {
                todoAdapter.setTodoList(todos);
            }
        });

        todoViewModel.fetchTodos();

    }
}