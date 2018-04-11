package com.example.findmenyc2.repositories;

import com.example.findmenyc2.models.Result;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ResultRepository extends CrudRepository<Result, Long> {
    List<Result> findAll();
}
