package com.example.findmenyc2.repositories;

import com.example.findmenyc2.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {

    List<User> findAll();

}