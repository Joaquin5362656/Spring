package com.example.demo.config;

import com.example.demo.entities.Book;
import com.example.demo.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner initDatabase(BookRepository repository) {
        return args -> {
            // Verificamos si la base está vacía para no duplicar datos en cada reinicio
            if (repository.count() == 0) {
                Book book1 = new Book(null, "Extranjero", "Camus", 120, 200.0, LocalDate.of(2003, 12, 8), true);
                Book book2 = new Book(null, "Noches Blancas", "Dovsto", 122, 300.0, LocalDate.of(1998, 3, 25), true);

                repository.save(book1);
                repository.save(book2);

                System.out.println("Base de datos inicializada con libros de prueba.");
            } else {
                System.out.println("La base de datos ya contiene datos, saltando inicialización.");
            }
        };
    }
}