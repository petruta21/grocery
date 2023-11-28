package com.tatsiana.grocery.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    @NotEmpty(message = "Enter a username")
    private String username;

    @NotEmpty(message = "Enter an email")
    @Email(message = "Email is not valid")
    private String email;

    @NotEmpty(message = "Enter a password")
    private String password;

    @NotEmpty(message = "Confirm your password")
    private String confirmPassword;

}
