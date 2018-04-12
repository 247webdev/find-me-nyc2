package com.example.resultsapi.controllers;

import com.example.resultsapi.factories.ResultFactory;
import com.example.resultsapi.models.Result;
import com.example.resultsapi.repositories.ResultRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class ResultsController {
    @Autowired
    private ResultRepository resultRepository;

    @Autowired
    private ResultFactory resultFactory;

    @GetMapping("/")
    public Iterable<Result> findAllResults() {
        return resultRepository.findAll();
    }

    @GetMapping("/{resultId}")
    public Result findResultById(@PathVariable Long resultId) throws NotFoundException {
        Result foundResult = resultRepository.findOne(resultId);

        if (foundResult == null) {
            throw new NotFoundException("Result with ID of " + resultId + " was not found!");
        }

        return foundResult;
    }

    @PostMapping("/")
    public Result createNewResult(@RequestBody Result newResult) throws IOException {

        Result modifiedResult = resultFactory.addLatitudeAndLongitudeToResult(newResult);
        return resultRepository.save(modifiedResult);
    }

    @PatchMapping("/{resultId}")
    public Result updateResultById(@PathVariable Long resultId, @RequestBody Result resultRequest) throws NotFoundException {

        Result resultFromDb = resultRepository.findOne(resultId);

        if (resultFromDb == null) {
            throw new NotFoundException("Result with ID of " + resultId + " was not found!");
        }

        resultFromDb.setTitle(resultRequest.getTitle());
        resultFromDb.setLocation(resultRequest.getLocation());
        resultFromDb.setLat(resultRequest.getLat());
        resultFromDb.setLng(resultRequest.getLng());
        resultFromDb.setSection(resultRequest.getSection());
        resultFromDb.setDescription(resultRequest.getDescription());
        resultFromDb.setDateAndTime(resultRequest.getDateAndTime());


        return resultRepository.save(resultFromDb);
    }

    @DeleteMapping("/{resultId}")
    public HttpStatus deleteResultById(@PathVariable Long resultId) throws EmptyResultDataAccessException {
        resultRepository.delete(resultId);
        return HttpStatus.OK;
    }

    // Exception handlers
    @ExceptionHandler
    void handleResultNotFound(
            NotFoundException exception,
            HttpServletResponse response) throws IOException {

        response.sendError(HttpStatus.NOT_FOUND.value(), exception.getMessage());
    }

    @ExceptionHandler
    void handleDeleteNotFoundException(
            EmptyResultDataAccessException exception,
            HttpServletResponse response) throws IOException {

        response.sendError(HttpStatus.NOT_FOUND.value());
    }

}
