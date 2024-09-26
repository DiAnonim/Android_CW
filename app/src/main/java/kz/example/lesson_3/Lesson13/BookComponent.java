package kz.example.lesson_3.Lesson13;

import dagger.Component;
import kz.example.lesson_3.MainActivity;

@Component(modules = BookModule.class)
public interface BookComponent {
    void inject(MainActivity_Lesson13 mainActivity13);
}
