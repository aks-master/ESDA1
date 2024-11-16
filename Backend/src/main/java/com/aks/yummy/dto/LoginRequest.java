package com.aks.yummy.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;

public record LoginRequest(@NotNull(message = "Email is required")
                           @Email(message = "Email must be in a valid format")
                           @JsonProperty("email")
                           String email,

                           @NotNull(message = "Password is required")
                           @NotEmpty(message = "Password should be present")
                           @NotBlank(message = "Password should be present")
                           @Size(min = 6, max = 12)
                           @JsonProperty("password")
                           String password) {
}
