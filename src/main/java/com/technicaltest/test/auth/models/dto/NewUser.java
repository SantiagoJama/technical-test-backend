package com.technicaltest.test.auth.models.dto;

import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
public class NewUser implements Serializable {

    @NotBlank(message = "username es obligatorio")
    private String username;
    @NotBlank(message = "password es obligatorio")
    private String password;
    @NotBlank(message = "password de confirmacion es obligatorio")
    private String passwordconfi;
    private Set<String> roles = new HashSet<>();
}
