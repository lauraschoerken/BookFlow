package com.bookflow.author.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookflow.author.model.entity.Author;

public interface AuthorRepository extends JpaRepository<Author, Long>{

}
