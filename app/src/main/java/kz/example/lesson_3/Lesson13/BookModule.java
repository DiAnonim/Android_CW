package kz.example.lesson_3.Lesson13;

import dagger.Module;
import dagger.Provides;

@Module
public class BookModule {
    @Provides
    BookRepository provideBookRepository() {
        return new BookRepository();
    }
}
