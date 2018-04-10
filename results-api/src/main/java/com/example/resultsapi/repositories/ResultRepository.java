package com.example.resultsapi.repositories;

import com.example.resultsapi.models.Result;
import org.springframework.data.repository.CrudRepository;

public interface ResultRepository extends CrudRepository<Result, Long> {
}
