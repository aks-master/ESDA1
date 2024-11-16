package com.aks.yummy.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CustomerResponse (
        @JsonProperty("First_name")
        String firstName,
        @JsonProperty("last_name")
        String lastName,
        @JsonProperty("email")
        String email,
        @JsonProperty("address")
        String address,

        @JsonProperty("city")
        String city,

        @JsonProperty("pincode")
        String pincode

) {
}
