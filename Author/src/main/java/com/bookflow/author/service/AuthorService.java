package com.bookflow.author.service;

import com.bookflow.author.model.entity.Author;
import com.bookflow.author.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    // Obtener todos los autores
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    // Obtener un autor por ID
    public Optional<Author> getAuthorById(Long id) {
        return authorRepository.findById(id);
    }

    // Guardar un nuevo autor
    public Author saveAuthor(Author author) {
        return authorRepository.save(author);
    }

    // Actualizar un autor existente
    public Author updateAuthor(Long id, Author authorDetails) {
        return authorRepository.findById(id).map(author -> {
            author.setName(authorDetails.getName());
            return authorRepository.save(author);
        }).orElseThrow(() -> new RuntimeException("Autor no encontrado con ID: " + id));
    }

    // Eliminar un autor por ID
    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }
}

