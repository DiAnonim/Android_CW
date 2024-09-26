package kz.example.lesson_3.Lesson13;

import javax.inject.Inject;

public class Car {
    private final Engine engine;

    @Inject
    public Car(Engine engine) {
        this.engine = engine;
    }

    public void drive(){
        engine.start();
        System.out.println("Engine start");
    }
}
