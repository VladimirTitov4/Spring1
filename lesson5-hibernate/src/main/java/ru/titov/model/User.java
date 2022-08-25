package ru.titov.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "users")
@NamedQueries({
        @NamedQuery(name = "findAllUsers", query = "Select u from User u"),
        @NamedQuery(name = "countAllUsers", query = "Select count(u) from User u"),
        @NamedQuery(name = "deleteUserById", query = "delete from User u where u.id = :id")
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @OneToMany(mappedBy = "user",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE},
            orphanRemoval = true)
    private List<Contact> contacts;

    @Column(nullable = false, length = 1024)
    private String password;

    @OneToOne(mappedBy = "user",
            cascade = {CascadeType.ALL},
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    private Customer customer;

    @ManyToMany(mappedBy = "users")
    private List<Role> roles;

    @Embedded
    private Passport passport;

    public User(String username, List<Contact> contacts, String password) {
        this.username = username;
        this.contacts = contacts;
        this.password = password;
    }
}
