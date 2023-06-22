package com.technicaltest.test.auth.models.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;

@Data
public class Login implements Serializable {
    @NotBlank(message = "Username es obligatorio")
    private String username;
    @NotBlank(message = "Password es obligatorio")
    private String password;
}
