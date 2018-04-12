package com.example.findmenyc2;

import com.example.findmenyc2.models.Result;
import com.example.findmenyc2.models.User;
import com.example.findmenyc2.repositories.ResultRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ResultsUIFeatureTest {

    @Autowired
    private ResultRepository resultRepository;

    @Before
    public void setUp() {  resultRepository.deleteAll();
    }

    @After
    public void tearDown() {  resultRepository.deleteAll();
    }

    @Test
    public void shouldAllowMapViewForAUserOnSearch() throws Exception {
        System.setProperty("selenide.browser", "Chrome");

        open("http://localhost:3000/search");

        $("#search-form").should(appear);
        $("#search-blurb").should(appear);
        $("#result-list").shouldNot(appear);

        $("#search-input").sendKeys("new search");
        $("#search-submit").click();

        $("#map-box").should(appear);
    }

//    @Test
//    public void shouldAllowUsersToSeeResultMarkersOnMap() throws Exception {
//        Result firstResult = new Result(
//                "result title 1",
//                "111 11th ave, san francisco ca",
//                111.12345,
//                -111.837432,
//                "public hearings 2",
//                "public hearings description here 1",
//                "date and time goes here 1"
//        );
//        firstResult = resultRepository.save(firstResult);
//        Long firstResultId = firstResult.getId();
//
//        Result secondResult = new Result(
//                "result title 2",
//                "222 22nd ave, san francisco ca",
//                122.12345,
//                -122.837432,
//                "public hearings 2",
//                "public hearings description here 2",
//                "date and time goes here 2"
//        );
//        secondResult = resultRepository.save(secondResult);
//        Long secondResultId = secondResult.getId();
//
//        System.setProperty("selenide.headless", "true");
//
//    }
}
