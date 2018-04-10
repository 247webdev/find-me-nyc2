package com.example.usersapi.controllers;

import com.example.usersapi.controllers.UsersController;
import com.example.usersapi.models.User;
import com.example.usersapi.repositories.UserRepository;
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

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UsersController.class)
public class UsersControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper jsonObjectMapper;

    @MockBean
    private UserRepository mockUserRepository;

    private User newUser;
    private User updatedSecondUser;

    @Before
    public void setUp() {
        User firstUser = new User(
                "user1",
                "First",
                "User",
                "first search",
                false
        );

        User secondUser = new User(
                "user2",
                "Second",
                "User",
                "second search",
                true
        );

        newUser = new User(
                "new_user",
                "New",
                "User",
                "new search",
                false
        );

        updatedSecondUser = new User(
                "updated_username",
                "Updated",
                "User",
                "updated search",
                true
        );

        List<User> mockUsers =
                Stream.of(firstUser, secondUser).collect(Collectors.toList());

        given(mockUserRepository.findAll()).willReturn(mockUsers);
        given(mockUserRepository.findOne(1L)).willReturn(firstUser);
        given(mockUserRepository.findOne(4L)).willReturn(null);

        // Mock out Delete to return EmptyResultDataAccessException for missing user with ID of 4
        doAnswer(invocation -> {
            throw new EmptyResultDataAccessException("oh no!", 1234);
        }).when(mockUserRepository).delete(4L);

        given(mockUserRepository.save(updatedSecondUser)).willReturn(updatedSecondUser);

    }

    @Test
    public void findAllUsers_success_returnsStatusOK() throws Exception {

        this.mockMvc
                .perform(get("/"))
                .andExpect(status().isOk());
    }

    @Test
    public void findAllUsers_success_returnAllUsersAsJSON() throws Exception {

        this.mockMvc
                .perform(get("/"))
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void findAllUsers_success_returnUserNameForEachUser() throws Exception {

        this.mockMvc
                .perform(get("/users"))
                .andExpect(jsonPath("$[0].userName", is("user1")));
    }

    @Test
    public void findAllUsers_success_returnFirstNameForEachUser() throws Exception {

        this.mockMvc
                .perform(get("/users"))
                .andExpect(jsonPath("$[0].firstName", is("First")));
    }

    @Test
    public void findAllUsers_success_returnLastNameForEachUser() throws Exception {

        this.mockMvc
                .perform(get("/users"))
                .andExpect(jsonPath("$[0].lastName", is("User")));
    }

    @Test
    public void findAllUsers_success_returnLastSearchForEachUser() throws Exception {

        this.mockMvc
                .perform(get("/users"))
                .andExpect(jsonPath("$[0].lastSearch", is("first search")));
    }

    @Test
    public void findAllUsers_success_returnIsAdminForEachUser() throws Exception {

        this.mockMvc
                .perform(get("/users"))
                .andExpect(jsonPath("$[0].admin", is(false)));
    }

    @Test
    public void findUserById_success_returnsStatusOK() throws Exception {

        this.mockMvc
                .perform(get("/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void findUserById_success_returnUserName() throws Exception {

        this.mockMvc
                .perform(get("/users/1"))
                .andExpect(jsonPath("$.userName", is("user1")));
    }

    @Test
    public void findUserById_success_returnFirstName() throws Exception {

        this.mockMvc
                .perform(get("/users/1"))
                .andExpect(jsonPath("$.firstName", is("First")));
    }

    @Test
    public void findUserById_success_returnLastName() throws Exception {

        this.mockMvc
                .perform(get("/users/1"))
                .andExpect(jsonPath("$.lastName", is("User")));
    }

    @Test
    public void findUserById_success_returnLastSearch() throws Exception {

        this.mockMvc
                .perform(get("/users/1"))
                .andExpect(jsonPath("$.lastSearch", is("first search")));
    }

    @Test
    public void findUserById_success_returnIsAdmin() throws Exception {

        this.mockMvc
                .perform(get("/users/1"))
                .andExpect(jsonPath("$.admin", is(false)));
    }

    @Test
    public void findUserById_failure_userNotFoundReturns404() throws Exception {

        this.mockMvc
                .perform(get("/4"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deleteUserById_success_returnsStatusOk() throws Exception {

        this.mockMvc
                .perform(delete("/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteUserById_success_deletesViaRepository() throws Exception {

        this.mockMvc.perform(delete("/1"));

        verify(mockUserRepository, times(1)).delete(1L);
    }

    @Test
    public void deleteUserById_failure_userNotFoundReturns404() throws Exception {

        this.mockMvc
                .perform(delete("/4"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void createUser_success_returnsStatusOk() throws Exception {

        this.mockMvc
                .perform(
                        post("/users")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonObjectMapper.writeValueAsString(newUser))
                )
                .andExpect(status().isOk());
    }

    @Test
    public void createUser_success_returnsUserName() throws Exception {

        this.mockMvc
                .perform(
                        post("/users")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonObjectMapper.writeValueAsString(newUser))
                )
                .andExpect(jsonPath("$.userName", is("new_user")));
    }

    @Test
    public void createUser_success_returnsFirstName() throws Exception {

        this.mockMvc
                .perform(
                        post("/users")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonObjectMapper.writeValueAsString(newUser))
                )
                .andExpect(jsonPath("$.firstName", is("New")));
    }

    @Test
    public void createUser_success_returnsLastName() throws Exception {

        this.mockMvc
                .perform(
                        post("/users")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonObjectMapper.writeValueAsString(newUser))
                )
                .andExpect(jsonPath("$.lastName", is("User")));
    }

    @Test
    public void createUser_success_returnsLastSearch() throws Exception {

        this.mockMvc
                .perform(
                        post("/users")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonObjectMapper.writeValueAsString(newUser))
                )
                .andExpect(jsonPath("$.lastSearch", is("new search")));
    }

    @Test
    public void createUser_success_returnsIsAdmin() throws Exception {

        this.mockMvc
                .perform(
                        post("/users")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonObjectMapper.writeValueAsString(newUser))
                )
                .andExpect(jsonPath("$.admin", is(false)));
    }

    @Test
    public void updateUserById_success_returnsStatusOk() throws Exception {

        this.mockMvc
                .perform(
                        patch("/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonObjectMapper.writeValueAsString(updatedSecondUser))
                )
                .andExpect(status().isOk());
    }

    @Test
    public void updateUserById_success_returnsUpdatedUserName() throws Exception {

        this.mockMvc
                .perform(
                        patch("/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonObjectMapper.writeValueAsString(updatedSecondUser))
                )
                .andExpect(jsonPath("$.userName", is("new_username")));
    }

    @Test
    public void updateUserById_success_returnsUpdatedFirstName() throws Exception {

        this.mockMvc
                .perform(
                        patch("/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonObjectMapper.writeValueAsString(updatedSecondUser))
                )
                .andExpect(jsonPath("$.firstName", is("new")));
    }

    @Test
    public void updateUserById_success_returnsUpdatedLastName() throws Exception {

        this.mockMvc
                .perform(
                        patch("/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonObjectMapper.writeValueAsString(updatedSecondUser))
                )
                .andExpect(jsonPath("$.lastName", is("name")));
    }

    @Test
    public void updateUserById_success_returnsUpdatedLastSearch() throws Exception {

        this.mockMvc
                .perform(
                        patch("/users/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonObjectMapper.writeValueAsString(updatedSecondUser))
                )
                .andExpect(jsonPath("$.lastSearch", is("updated search")));
    }

    @Test
    public void updateUserById_success_returnsUpdatedIsAdmin() throws Exception {

        this.mockMvc
                .perform(
                        patch("/users/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonObjectMapper.writeValueAsString(updatedSecondUser))
                )
                .andExpect(jsonPath("$.admin", is(true)));
    }

    @Test
    public void updateUserById_failure_userNotFoundReturns404() throws Exception {

        this.mockMvc
                .perform(
                        patch("/4")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonObjectMapper.writeValueAsString(updatedSecondUser))
                )
                .andExpect(status().isNotFound());
    }

}
