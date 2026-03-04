package com.example.demo;

import com.example.demo.entities.Book;
import com.example.demo.repository.BookRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(DemoApplication.class, args);
		BookRepository repository = context.getBean(BookRepository.class);


		// CRUD

		// crear libros
		Book book1 = new Book(null, "Extranjero", "Camus", 120, 200.0, LocalDate.of(2003, 12, 8), true);
		Book book2 = new Book(null, "Noches Blancas", "Dovsto", 122, 300.0, LocalDate.of(1998, 3, 25), true);

		// almacenar libro
		System.out.println("Cantidad de libros en la db: " + repository.findAll().size());

		repository.save(book1);
		repository.save(book2);

		System.out.println("Cantidad de libros en la db: " + repository.findAll().size());

		// repository.deleteById(1L);
		// System.out.println("Cantidad de libros en la db: " + repository.findAll().size());
	}
}
