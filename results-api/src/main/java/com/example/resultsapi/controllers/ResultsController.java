package com.example.resultsapi.controllers;

import com.example.resultsapi.models.Result;
import com.example.resultsapi.repositories.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResultsController {
    @Autowired
    private ResultRepository resultRepository;

    @GetMapping("/")
    public Iterable<Result> findAllResults() {
        return resultRepository.findAll();
    }

    @PostMapping("/")
    public Result createNewResult(@RequestBody Result newResult) {
        return resultRepository.save(newResult);
    }

}
