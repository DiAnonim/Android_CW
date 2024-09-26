package kz.example.lesson_3.Lesson13;



public class MainClass {
    public static void main(String[] args) {
        CarComponent carComponent = DaggerCarComponent.create();

        Car car = carComponent.getCar();
        car.drive();
    }
}
