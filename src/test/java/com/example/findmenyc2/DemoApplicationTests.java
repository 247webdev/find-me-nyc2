package com.example.findmenyc2;

import com.example.findmenyc2.models.User;
import com.example.findmenyc2.repositories.UserRepository;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.Stream;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.core.Is.is;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class DemoApplicationTests {

    @Autowired
    private UserRepository userRepository;

    User secondUser;

    @Before
    public void setUp() {
        userRepository.deleteAll();

        User firstUser = new User(
                "user1",
                "First",
                "User",
                "first search",
                false
        );

        secondUser = new User(
                "user2",
                "Second",
                "User",
                "second search",
                true
        );

        Stream.of(firstUser, secondUser)
                .forEach(user -> {
                    userRepository.save(user);
                });
    }

    @After
    public void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    public void shouldAllowFullCrudForAUser() throws Exception {

        User userNotYetInDb = new User(
                "new_user",
                "Third",
                "User",
                "third search",
                false
        );

        // Test adding a new user
        given()
                .contentType(JSON)
                .and().body(userNotYetInDb)
                .when()
                .post("http://localhost:8081")
                .then()
                .statusCode(Matchers.is(200))
                .and().body(Matchers.containsString("new_user"));

        // Test get all Users
        when()
                .get("http://localhost:8081")
                .then()
                .statusCode(Matchers.is(200))
                .and().body(Matchers.containsString("user1"))
                .and().body(Matchers.containsString("user2"))
                .and().body(Matchers.containsString("new_user"));

        // Test finding one user by ID
        when()
                .get("http://localhost:8081/" + secondUser.getId())
                .then()
                .statusCode(Matchers.is(200))
                .and().body(Matchers.containsString("Second"))
                .and().body(Matchers.containsString("User"));


        // Test updating a user
        secondUser.setFirstName("changed_name");

        given()
                .contentType(JSON)
                .and().body(secondUser)
                .when()
                .patch("http://localhost:8081/" + secondUser.getId())
                .then()
                .statusCode(Matchers.is(200))
                .and().body(Matchers.containsString("changed_name"));

        // Test deleting a user
        when()
                .delete("http://localhost:8081/" + secondUser.getId())
                .then()
                .statusCode(Matchers.is(200));



    }


}