package com.example.findmenyc2;

import com.example.findmenyc2.models.User;
import com.example.findmenyc2.repositories.UserRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class UsersUIFeatureTest {
    @Autowired
    private UserRepository userRepository;

    @Before
    public void setUp() {
        userRepository.deleteAll();
    }

    @After
    public void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    public void shouldAllowViewDeleteAndAdminOnlyEditFunctionalityForAUser() throws Exception {

        User firstUser = new User(
                "user1",
                "First",
                "User",
                "first search",
                false
        );
        firstUser = userRepository.save(firstUser);
        Long firstUserId = firstUser.getId();

        User secondUser = new User(
                "user2",
                "Second",
                "User",
                "second search",
                true
        );
        secondUser = userRepository.save(secondUser);
        Long secondUserId = secondUser.getId();

        System.setProperty("selenide.headless", "true");

        open("http://localhost:3000/admin");

        $$("[data-user-display]").shouldHave(size(2));

        // Test that all data shows up for each user
        $("#user-" + firstUserId + "-user-name").shouldHave(text("user1"));
        $("#user-" + firstUserId + "-first-and-last-name").shouldHave(text("First User"));
        $("#user-" + firstUserId + "-last-search").shouldHave(text("first search"));
        $("#user-" + firstUserId + "-admin").shouldHave(text("No"));

        $("#user-" + secondUserId + "-user-name").shouldHave(text("user2"));
        $("#user-" + secondUserId + "-first-and-last-name").shouldHave(text("Second User"));
        $("#user-" + secondUserId + "-last-search").shouldHave(text("second search"));
        $("#user-" + secondUserId + "-admin").shouldHave(text("Yes"));

        // Test admin attribute update for second user
//        $("#user-" + secondUserId + "-admin").selectOption("No");
//        $("#user-" + secondUserId + "-admin").shouldHave(text("No"));
//
//        refresh();
//
//        $("#user-" + secondUserId + "-admin").shouldHave(text("No"));

        // Test Deleting the first user
        $("#user-" + firstUserId).should(exist);
        $$("[data-user-display]").shouldHave(size(2));

        $("#delete-user-" + firstUserId).click();
        $("#user-" + firstUserId).shouldNot(exist);

        $$("[data-user-display]").shouldHave(size(1));

    }

    @Test
    public void shouldAllowSignupFunctionalityForAUser() throws Exception {

        System.setProperty("selenide.browser", "Chrome");

        open("http://localhost:3000/signup");

        $("#signup-form").should(appear);

        // Add a new user
        $("#new-user-user-name").sendKeys("third_user");
        $("#new-user-first-name").sendKeys("Third");
        $("#new-user-last-name").sendKeys("User");
        $("#new-user-submit").click();

        // Make sure we're now on the search page
        $("#search-wrapper").should(appear);

        //Go to admin page
        open("http://localhost:3000/admin");

        //check to see if users wrapper is on the page
        $("#users-wrapper").should(appear);

        // Now there should be one user - the recently created one
        $$("[data-user-display]").shouldHave(size(1));

        refresh();

        // Now there should be one user again after the refresh
        $$("[data-user-display]").shouldHave(size(1));

        // Check that the data is showing up for the User
        Long thirdUserId = 1L;
        $("#user-" + thirdUserId + "-user-name").shouldHave(text("third_user"));
        $("#user-" + thirdUserId + "-first-and-last-name").shouldHave(text("Third User"));

    }

    @Test
    public void shouldAllowLoginFunctionalityForAUser() throws Exception {
        User firstUser = new User(
                "user1",
                "First",
                "User",
                "first search",
                false
        );
        firstUser = userRepository.save(firstUser);
        String firstUserUsername = firstUser.getUserName();

        System.setProperty("selenide.browser", "Chrome");

        open("http://localhost:3000/login");

        $("#login-form").should(appear);

        // Add a new user
        $("#login-user-name").sendKeys("user1");
        $("#user-login-submit").click();

        // Make sure we're now on the search page again
        $("#search-wrapper").should(appear);

    }

    @Test
    public void shouldAllowSearchFunctionalityForAUser() throws Exception {
        System.setProperty("selenide.browser", "Chrome");

        open("http://localhost:3000/search");

        $("#search-form").should(appear);
        $("#search-blurb").should(appear);
        $("#result-list").shouldNot(appear);

        $("#search-input").sendKeys("new search");
        $("#search-submit").click();

        $("#result-list").should(appear);
        $("#result-item").should(appear);

        $$("[data-result-display]").shouldHave(size(20));
    }
}
