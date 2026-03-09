package com.example.demo.controller;

import com.example.demo.entities.Book;
import com.example.demo.repository.BookRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Tag(name = "Books", description = "REST API for managing books in the library")
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
    @Operation(summary = "List all books", description = "Return a complete list of books stored in the database")
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
    @Operation(summary = "search book by ID", description = "Search for a specific book using its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book found successfully"),
            @ApiResponse(responseCode = "404", description = "The book does not exist in the database")
    })
    public ResponseEntity<Book> findById(@Parameter(description = "book id", example = "1")
                                             @PathVariable Long id){
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
    @Operation(summary = "Create a new book", description = "Store a new book. Do not include an ID in the request body.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Book created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request (for example, trying to send an ID)")
    })
    public ResponseEntity<Book> create(@RequestBody Book book){
        if (book.getId() != null){ // existe el Id, por lo que no es una creacion
            log.warn("Trying to create a book with an existing ID");
            return ResponseEntity.badRequest().build();
        }

        Book bookCreated = bookRepository.save(book);
        return ResponseEntity.ok(bookCreated);
    }


    // Actualizar un libro de la db
    @PutMapping("/api/books")
    @Operation(summary = "Update a book", description = "Modify an existing book. The ID is required in the request body.")
    public ResponseEntity<Book> update(@RequestBody Book book){

        if (book.getId() == null || !bookRepository.existsById(book.getId()))  { // si no existe el Id, es una creacion
            log.warn("Trying to update a non existent book or one without an ID");
            return ResponseEntity.badRequest().build();
        }

        // proceso de actualizacion
        Book bookUpdated = bookRepository.save(book);
        return ResponseEntity.ok(bookUpdated);
    }


    // Borrar un libro de la db
    @DeleteMapping("api/books/{id}")
    @Operation(summary = "Delete a book", description = "Delete a book from the database permanently by its ID")
    public ResponseEntity<Book> delete(@PathVariable Long id){

        if (!bookRepository.existsById(id)){
            log.warn("Trying to delete a non existent book");
            return ResponseEntity.badRequest().build();
        }

        bookRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }


    // Borrar todos los libro de la db
    @DeleteMapping("api/books")
    @Operation(summary = "Delete all books", description = "Delete all books from the database permanently")
    public ResponseEntity<Book> deleteAll(){
        log.info("REST request for delete all books");
        bookRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }
}
