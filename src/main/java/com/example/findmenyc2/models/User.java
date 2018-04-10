package com.example.findmenyc2.models;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor @NoArgsConstructor @Getter @Setter
@Entity @Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "LAST_SEARCH")
    private String lastSearch;

    @Column(name = "ISADMIN")
    private boolean isAdmin;

    public User(String userName, String firstName, String lastName, String lastSearch, boolean isAdmin) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.lastSearch = lastSearch;
        this.isAdmin = isAdmin;
    }
}
