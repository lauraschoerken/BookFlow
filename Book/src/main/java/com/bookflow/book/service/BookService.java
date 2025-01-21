package com.bookflow.book.service;


import com.bookflow.book.model.entity.Book;
import com.bookflow.book.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    // Crear un nuevo libro
    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    // Obtener todos los libros
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // Obtener un libro por su ID
    public Book getBookById(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        return book.orElse(null);  // Devuelve null si no se encuentra el libro
    }

    // Actualizar un libro
    public Book updateBook(Long id, Book bookDetails) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            book.setTitle(bookDetails.getTitle());
            book.setAuthor(bookDetails.getAuthor());
            book.setIsbn(bookDetails.getIsbn());
            return bookRepository.save(book);
        }
        return null;  // Si no se encuentra el libro, devuelve null
    }

    // Eliminar un libro
    public boolean deleteBook(Long id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            bookRepository.delete(optionalBook.get());
            return true;
        }
        return false;
    }
}
