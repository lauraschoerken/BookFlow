package com.bookflow.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookflow.user.model.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
