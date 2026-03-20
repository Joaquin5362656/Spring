package com.example.demo.controller;

import com.example.demo.entities.Book;
import com.example.demo.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookControllerTest {

    private TestRestTemplate testRestTemplate;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @Autowired
    private BookRepository bookRepository;

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        // Limpiamos la base de datos antes de cada test para asegurar independencia
        bookRepository.deleteAll();
        restTemplateBuilder = restTemplateBuilder.rootUri("http://localhost:" + port);
        testRestTemplate = new TestRestTemplate(restTemplateBuilder);
    }

    @Test
    void findAll() {
        // Insertamos uno manual para asegurar que la lista no venga vacía si queremos probar tamaño
        bookRepository.save(new Book(null, "Test Book", "Author", 100, 10.0, null, true));

        ResponseEntity<Book[]> response = testRestTemplate.getForEntity("/api/books", Book[].class);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        List<Book> books = Arrays.asList(response.getBody());
        assertTrue(books.size() >= 1);
    }

    @Test
    void findById() {
        // Buscamos un ID que sabemos que no existe porque acabamos de hacer deleteAll()
        ResponseEntity<Book> response = testRestTemplate.getForEntity("/api/books/999", Book.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void create() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        String json = """
                {
                    "title": "80 vueltas en 80 dias SpringTest",
                    "author": "Berner",
                    "pages": 140,
                    "price": 800.0,
                    "releaseDate": "1994-02-18",
                    "onLine": true
                }
                """;

        HttpEntity<String> request = new HttpEntity<>(json, headers);
        ResponseEntity<Book> response = testRestTemplate.exchange("/api/books", HttpMethod.POST, request, Book.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        Book result = response.getBody();
        assertNotNull(result);
        assertNotNull(result.getId());
        assertEquals("Berner", result.getAuthor());
    }
}