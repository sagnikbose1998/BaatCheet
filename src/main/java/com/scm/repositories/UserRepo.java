package com.scm.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.scm.entites.User;

public interface UserRepo extends JpaRepository<User,String> {

    Optional<User> findByEmail(String email);

    Optional<User> findByEmailAndPassword(String email,String password);


}