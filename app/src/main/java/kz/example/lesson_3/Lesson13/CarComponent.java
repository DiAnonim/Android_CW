package kz.example.lesson_3.Lesson13;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {CarModule.class})
public interface CarComponent {
    Car getCar();

}
