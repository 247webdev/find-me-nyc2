package com.example.resultsapi.factories;

import com.example.resultsapi.models.Result;
import com.example.resultsapi.repositories.ResultRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ResultFactoryTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ResultFactory mockResultFactory;

    private Result updatedResult;

    @Before
    public void setUp() {
        Result firstResult = new Result(
                "result title 1",
                "111 11th ave, san francisco ca",
                0.0,
                0.0,
                "public hearings 2",
                "public hearings description here 1",
                "date and time goes here 1"
        );

        updatedResult = new Result(
                "updated result title",
                "updated address",
                122.12345,
                -122.837432,
                "updated section",
                "updated description",
                "updated date and time"
        );

        List<Result> mockResults =
                Stream.of(firstResult).collect(Collectors.toList());

        given(mockResultFactory.save(updatedResult)).willReturn(updatedResult);
    }

    @Test
    public void updateResultById_success_returnsUpdatedLat() throws Exception {

        this.mockMvc
                .perform(
                        patch("/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonObjectMapper.writeValueAsString(updatedResult))
                )
                .andExpect(jsonPath("$.lat", is(122.12345)));
    }

    @Test
    public void updateResultById_success_returnsUpdatedLng() throws Exception {

        this.mockMvc
                .perform(
                        patch("/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonObjectMapper.writeValueAsString(updatedResult))
                )
                .andExpect(jsonPath("$.lng", is(-122.837432)));
    }
}
