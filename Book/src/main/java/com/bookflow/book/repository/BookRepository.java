package com.bookflow.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookflow.book.model.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long>{

}
