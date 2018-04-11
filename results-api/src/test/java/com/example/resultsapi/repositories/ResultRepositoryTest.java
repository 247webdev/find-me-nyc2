package com.example.resultsapi.repositories;

import com.example.resultsapi.models.Result;
import com.google.common.collect.Iterables;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
public class ResultRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ResultRepository resultRepository;

    @Before
    public void setUp() {
        resultRepository.deleteAll();

        Result firstResult = new Result(
                "result title 1",
                "111 11th ave, san francisco ca",
                111.12345,
                -111.837432,
                "public hearings 2",
                "public hearings description here 1",
                "date and time goes here 1"
        );

        Result secondResult = new Result(
                "result title 2",
                "222 22nd ave, san francisco ca",
                122.12345,
                -122.837432,
                "public hearings 2",
                "public hearings description here 2",
                "date and time goes here 2"
        );

        entityManager.persist(firstResult);
        entityManager.persist(secondResult);
        entityManager.flush();
    }

    @Test
    public void findAll_returnsAllResults() {

        Iterable<Result> resultsFromDb = resultRepository.findAll();
        long size = resultsFromDb.spliterator().getExactSizeIfKnown();
        assertThat(size, is(2L));
    }

    @Test
    public void findAll_returnsTitle() {
        Iterable<Result> resultsFromDb = resultRepository.findAll();

        String secondResultsTitle= Iterables.get(resultsFromDb, 1).getTitle();

        assertThat(secondResultsTitle, is("result title 2"));
    }

    @Test
    public void findAll_returnsLocation() {
        Iterable<Result> resultsFromDb = resultRepository.findAll();

        String secondResultsLocation= Iterables.get(resultsFromDb, 1).getLocation();

        assertThat(secondResultsLocation, is("222 22nd ave, san francisco ca"));
    }

    @Test
    public void findAll_returnsLat() {
        Iterable<Result> resultsFromDb = resultRepository.findAll();

        double secondResultsLat= Iterables.get(resultsFromDb, 1).getLat();

        assertThat(secondResultsLat, is(122.12345));
    }

    @Test
    public void findAll_returnsLng() {
        Iterable<Result> resultsFromDb = resultRepository.findAll();

        double secondResultsLng= Iterables.get(resultsFromDb, 1).getLng();

        assertThat(secondResultsLng, is(-122.837432));
    }

    @Test
    public void findAll_returnsSection() {
        Iterable<Result> resultsFromDb = resultRepository.findAll();

        String secondResultsSection= Iterables.get(resultsFromDb, 1).getSection();

        assertThat(secondResultsSection, is("public hearings 2"));
    }

    @Test
    public void findAll_returnsDescription() {
        Iterable<Result> resultsFromDb = resultRepository.findAll();

        String secondResultsDescription= Iterables.get(resultsFromDb, 1).getDescription();

        assertThat(secondResultsDescription, is("public hearings description here 2"));
    }

    @Test
    public void findAll_returnsDateAndTime() {
        Iterable<Result> resultsFromDb = resultRepository.findAll();

        String secondResultsDateAndTime= Iterables.get(resultsFromDb, 1).getDateAndTime();

        assertThat(secondResultsDateAndTime, is("date and time goes here 2"));
    }

}
