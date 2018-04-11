package com.example.resultsapi.controllers;

import com.example.resultsapi.models.Result;
import com.example.resultsapi.repositories.ResultRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ResultsController.class)
public class ResultsControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper jsonObjectMapper;

    @MockBean
    private ResultRepository mockResultRepository;

    private Result newResult;
    private Result updatedSecondResult;

    @Before
    public void setUp() {
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

        newResult = new Result(
                "new result title",
                "new address",
                122.12345,
                -122.837432,
                "new section",
                "new description",
                "new date and time"
        );

        updatedSecondResult = new Result(
                "updated result title",
                "updated address",
                122.12345,
                -122.837432,
                "updated section",
                "updated description",
                "updated date and time"
        );

        List<Result> mockResults =
                Stream.of(firstResult, secondResult).collect(Collectors.toList());

        given(mockResultRepository.findAll()).willReturn(mockResults);
        given(mockResultRepository.findOne(1L)).willReturn(firstResult);
        given(mockResultRepository.findOne(4L)).willReturn(null);
        given(mockResultRepository.save(newResult)).willReturn(newResult);
        given(mockResultRepository.save(updatedSecondResult)).willReturn(updatedSecondResult);

        // Mock out Delete to return EmptyResultDataAccessException for missing user with ID of 4
        doAnswer(invocation -> {
            throw new EmptyResultDataAccessException("ERROR MESSAGE FROM MOCK!!!", 1234);
        }).when(mockResultRepository).delete(4L);
    }

    @Test
    public void findAllResults_success_returnsStatusOK() throws Exception {

        this.mockMvc
                .perform(get("/"))
                .andExpect(status().isOk());
    }

    @Test
    public void findAllResults_success_returnAllUsersAsJSON() throws Exception {

        this.mockMvc
                .perform(get("/"))
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void findAllResults_success_returnTitleForEachResult() throws Exception {

        this.mockMvc
                .perform(get("/"))
                .andExpect(jsonPath("$[0].title", is("result title 1")));
    }

    @Test
    public void findAllResults_success_returnLocationForEachResult() throws Exception {

        this.mockMvc
                .perform(get("/"))
                .andExpect(jsonPath("$[0].location", is("111 11th ave, san francisco ca")));
    }

    @Test
    public void findAllResults_success_returnLatForEachResult() throws Exception {

        this.mockMvc
                .perform(get("/"))
                .andExpect(jsonPath("$[0].lat", is(111.12345)));
    }

    @Test
    public void findAllResults_success_returnLngForEachResult() throws Exception {

        this.mockMvc
                .perform(get("/"))
                .andExpect(jsonPath("$[0].lng", is(-111.837432)));
    }

    @Test
    public void findAllResults_success_returnSectionForEachResult() throws Exception {

        this.mockMvc
                .perform(get("/"))
                .andExpect(jsonPath("$[0].section", is("public hearings 2")));
    }

    @Test
    public void findAllResults_success_returnDescriptionForEachResult() throws Exception {

        this.mockMvc
                .perform(get("/"))
                .andExpect(jsonPath("$[0].description", is("public hearings description here 1")));
    }

    @Test
    public void findAllResults_success_returnDateAndTimeForEachResult() throws Exception {

        this.mockMvc
                .perform(get("/"))
                .andExpect(jsonPath("$[0].dateAndTime", is("date and time goes here 1")));
    }

    @Test
    public void findResultById_success_returnsStatusOK() throws Exception {

        this.mockMvc
                .perform(get("/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void findResultById_success_returnTitle() throws Exception {

        this.mockMvc
                .perform(get("/1"))
                .andExpect(jsonPath("$.title", is("result title 1")));
    }

    @Test
    public void findResultById_success_returnLocation() throws Exception {

        this.mockMvc
                .perform(get("/1"))
                .andExpect(jsonPath("$.location", is("111 11th ave, san francisco ca")));
    }

    @Test
    public void findResultById_success_returnLat() throws Exception {

        this.mockMvc
                .perform(get("/1"))
                .andExpect(jsonPath("$.lat", is(111.12345)));
    }

    @Test
    public void findResultById_success_returnLng() throws Exception {

        this.mockMvc
                .perform(get("/1"))
                .andExpect(jsonPath("$.lng", is(-111.837432)));
    }

    @Test
    public void findResultById_success_returnSection() throws Exception {

        this.mockMvc
                .perform(get("/1"))
                .andExpect(jsonPath("$.section", is("public hearings 2")));
    }

    @Test
    public void findResultById_success_returnDescription() throws Exception {

        this.mockMvc
                .perform(get("/1"))
                .andExpect(jsonPath("$.description", is("public hearings description here 1")));
    }

    @Test
    public void findResultById_success_returnDateAndTime() throws Exception {

        this.mockMvc
                .perform(get("/1"))
                .andExpect(jsonPath("$.dateAndTime", is("date and time goes here 1")));
    }

    @Test
    public void findResultById_failure_resultNotFoundReturns404() throws Exception {

        this.mockMvc
                .perform(get("/4"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void findResultById_failure_resultNotFoundReturnsNotFoundErrorMessage() throws Exception {

        this.mockMvc
                .perform(get("/4"))
                .andExpect(status().reason(containsString("Result with ID of 4 was not found!")));
    }

    @Test
    public void deleteResultById_success_returnsStatusOk() throws Exception {

        this.mockMvc
                .perform(delete("/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteResultById_success_deletesViaRepository() throws Exception {

        this.mockMvc.perform(delete("/1"));

        verify(mockResultRepository, times(1)).delete(1L);
    }

    @Test
    public void deleteResultById_failure_resultNotFoundReturns404() throws Exception {

        this.mockMvc
                .perform(delete("/4"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void createResult_success_returnsStatusOk() throws Exception {

        this.mockMvc
                .perform(
                        post("/")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonObjectMapper.writeValueAsString(newResult))
                )
                .andExpect(status().isOk());
    }

    @Test
    public void createResult_success_returnsTile() throws Exception {

        this.mockMvc
                .perform(
                        post("/")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonObjectMapper.writeValueAsString(newResult))
                )
                .andExpect(jsonPath("$.title", is("new result title")));
    }

    @Test
    public void createResult_success_returnsLocation() throws Exception {

        this.mockMvc
                .perform(
                        post("/")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonObjectMapper.writeValueAsString(newResult))
                )
                .andExpect(jsonPath("$.location", is("new address")));
    }

    @Test
    public void createResult_success_returnsLat() throws Exception {

        this.mockMvc
                .perform(
                        post("/")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonObjectMapper.writeValueAsString(newResult))
                )
                .andExpect(jsonPath("$.lat", is(122.12345)));
    }

    @Test
    public void createResult_success_returnsLng() throws Exception {

        this.mockMvc
                .perform(
                        post("/")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonObjectMapper.writeValueAsString(newResult))
                )
                .andExpect(jsonPath("$.lng", is(-122.837432)));
    }

    @Test
    public void createResult_success_returnsSection() throws Exception {

        this.mockMvc
                .perform(
                        post("/")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonObjectMapper.writeValueAsString(newResult))
                )
                .andExpect(jsonPath("$.section", is("new section")));
    }

    @Test
    public void createResult_success_returnsDescription() throws Exception {

        this.mockMvc
                .perform(
                        post("/")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonObjectMapper.writeValueAsString(newResult))
                )
                .andExpect(jsonPath("$.description", is("new description")));
    }

    @Test
    public void createResult_success_returnsDateAndTime() throws Exception {

        this.mockMvc
                .perform(
                        post("/")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonObjectMapper.writeValueAsString(newResult))
                )
                .andExpect(jsonPath("$.dateAndTime", is("new date and time")));
    }

    @Test
    public void updateResultById_success_returnsStatusOk() throws Exception {

        this.mockMvc
                .perform(
                        patch("/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonObjectMapper.writeValueAsString(updatedSecondResult))
                )
                .andExpect(status().isOk());
    }

    @Test
    public void updateResultById_success_returnsUpdatedTitle() throws Exception {

        this.mockMvc
                .perform(
                        patch("/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonObjectMapper.writeValueAsString(updatedSecondResult))
                )
                .andExpect(jsonPath("$.title", is("updated result title")));
    }

    @Test
    public void updateResultById_success_returnsUpdatedLocation() throws Exception {

        this.mockMvc
                .perform(
                        patch("/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonObjectMapper.writeValueAsString(updatedSecondResult))
                )
                .andExpect(jsonPath("$.location", is("updated address")));
    }

    @Test
    public void updateResultById_success_returnsUpdatedLat() throws Exception {

        this.mockMvc
                .perform(
                        patch("/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonObjectMapper.writeValueAsString(updatedSecondResult))
                )
                .andExpect(jsonPath("$.lat", is(122.12345)));
    }

    @Test
    public void updateResultById_success_returnsUpdatedLng() throws Exception {

        this.mockMvc
                .perform(
                        patch("/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonObjectMapper.writeValueAsString(updatedSecondResult))
                )
                .andExpect(jsonPath("$.lng", is(-122.837432)));
    }

    @Test
    public void updateResultById_success_returnsUpdatedSection() throws Exception {

        this.mockMvc
                .perform(
                        patch("/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonObjectMapper.writeValueAsString(updatedSecondResult))
                )
                .andExpect(jsonPath("$.section", is("updated section")));
    }

    @Test
    public void updateResultById_success_returnsUpdatedDescription() throws Exception {

        this.mockMvc
                .perform(
                        patch("/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonObjectMapper.writeValueAsString(updatedSecondResult))
                )
                .andExpect(jsonPath("$.description", is("updated description")));
    }

    @Test
    public void updateResultById_success_returnsUpdatedDateAndTime() throws Exception {

        this.mockMvc
                .perform(
                        patch("/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonObjectMapper.writeValueAsString(updatedSecondResult))
                )
                .andExpect(jsonPath("$.dateAndTime", is("updated date and time")));
    }

    @Test
    public void updateResultById_failure_resultNotFoundReturnsNotFoundErrorMessage() throws Exception {

        this.mockMvc
                .perform(
                        patch("/4")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonObjectMapper.writeValueAsString(updatedSecondResult))
                )
                .andExpect(status().reason(containsString("Result with ID of 4 was not found!")));
    }
}
