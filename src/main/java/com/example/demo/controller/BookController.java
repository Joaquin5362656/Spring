package com.example.demo.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.example.demo.entities.Book;
import com.example.demo.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {

    private final Logger log = LoggerFactory.getLogger(BookController.class);

    // atributos
    private BookRepository bookRepository;

    // constructores
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // con tener ese aributo y constructor lo que hace Spring es decir que para crear el Bean
    // BookController (ya que tiene la anotacion de @RestController) necesita un BookRepository
    // y busca en el contenedor de Beans y encuentra el Bean correspondiente a BookRepository
    // (ya que tiene la anotacion @Repository) y lo inyecta automaticamente

    // CRUD sobre la entidad Book


    // Buscar todos los libros

    /*
    *   http://localhost:8080/api/books
    *   @return
    */
    @GetMapping("/api/books")
    public List<Book> findAll(){
        return bookRepository.findAll();
    }


    /*
    * @Param id
    * @return
    *
    * */

    // Buscar un solo libro en la db por ID
    @GetMapping("/api/books/{id}")
    public ResponseEntity<Book> findById(@PathVariable Long id){
        Optional<Book> bookOptional = bookRepository.findById(id);

        // opcion 1:
        if (bookOptional.isPresent()){
            return ResponseEntity.ok(bookOptional.get());
        }
        return ResponseEntity.notFound().build();

        // opcion 2:
        // return bookOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /*
     * @Param book
     * @return
     *
     * */
    // Crear un libro a la db
    @PostMapping("/api/books")
    public ResponseEntity<Book> created(@RequestBody Book book){
        if (book.getId() != null){ // existe el Id, por lo que no es una creacion
            log.warn("trying to create a book with Id");
            System.out.println("trying to create a book with Id");
            return ResponseEntity.badRequest().build();
        }

        Book bookCreated = bookRepository.save(book);
        return ResponseEntity.ok(bookCreated);
    }


    // Actualizar un libro de la db
    @PutMapping("/api/books")
    public ResponseEntity<Book> update(@RequestBody Book book){

        if (book.getId() == null){ // si no existe el Id, es una creacion
            log.warn("Trying to update a non existent book");
            return ResponseEntity.badRequest().build();
        }

        if (!bookRepository.existsById(book.getId())){
            log.warn("Trying to update a non existent book");
            return ResponseEntity.badRequest().build();
        }

        // proceso de actualizacion
        Book bookUpdated = bookRepository.save(book);
        return ResponseEntity.ok(bookUpdated);
    }


    // Borrar un libro de la db
    @DeleteMapping("api/books/{id}")
    public ResponseEntity<Book> delete(@PathVariable Long id){

        if (!bookRepository.existsById(id)){
            log.warn("Trying to delete a non existent book");
            return ResponseEntity.badRequest().build();
        }

        bookRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }


    // Borrar un libro de la db
    @DeleteMapping("api/books")
    public ResponseEntity<Book> deleteAll(){
        log.info("REST request for delete all books");
        bookRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }
}
