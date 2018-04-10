package com.example.usersapi.repositories;

import com.example.usersapi.models.User;
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
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

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

        User secondUser = new User(
                "user2",
                "Second",
                "User",
                "second search",
                true
        );

        entityManager.persist(firstUser);
        entityManager.persist(secondUser);
        entityManager.flush();
    }

    @Test
    public void findAll_returnsAllUsers() {

        List<User> usersFromDb = userRepository.findAll();

        assertThat(usersFromDb.size(), is(2));
    }

    @Test
    public void findAll_returnsUserName() {
        List<User> usersFromDb = userRepository.findAll();
        String secondUsersUserName = usersFromDb.get(1).getUserName();

        assertThat(secondUsersUserName, is("user2"));
    }

    @Test
    public void findAll_returnsFirstName() {
        List<User> usersFromDb = userRepository.findAll();
        String secondUsersFirstName = usersFromDb.get(1).getFirstName();

        assertThat(secondUsersFirstName, is("Second"));
    }

    @Test
    public void findAll_returnsLastName() {
        List<User> usersFromDb = userRepository.findAll();
        String secondUsersLastName = usersFromDb.get(1).getLastName();

        assertThat(secondUsersLastName, is("User"));
    }

    @Test
    public void findAll_returnsLastSearch() {
        Iterable<User> usersFromDb = userRepository.findAll();

        String secondUsersLastSearch = Iterables.get(usersFromDb, 1).getLastSearch();

        assertThat(secondUsersLastSearch, is("second search"));
    }

    @Test
    public void findAll_returnsIsAdmin() {
        Iterable<User> usersFromDb = userRepository.findAll();

        boolean secondUsersIsAdmin= Iterables.get(usersFromDb, 1).isAdmin();

        assertThat(secondUsersIsAdmin, is(true));
    }

}
