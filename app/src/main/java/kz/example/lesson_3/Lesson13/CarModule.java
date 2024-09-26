package kz.example.lesson_3.Lesson13;

import dagger.Module;
import dagger.Provides;

@Module
public class CarModule {
    @Provides
    Engine provideEngine(){
        return new Engine();
    }

    @Provides
    Car provideCar(Engine engine){
        return new Car(engine);
    }
}
