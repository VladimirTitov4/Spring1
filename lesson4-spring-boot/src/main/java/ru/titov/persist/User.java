package ru.titov.persist;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
public class User {

    private Long id;

    @NotBlank(message = "can not be empty!!!")
    private String username;

    @NotBlank
    @Email
    private String email;

    @Pattern(regexp = "^(?=.*?[0-9])(?=.*?[A-Z]).{8,}$", message = "Password too simple")
    private String password;

    private String matchingPassword;

    public User(String username) {
        this.username = username;
    }
}
