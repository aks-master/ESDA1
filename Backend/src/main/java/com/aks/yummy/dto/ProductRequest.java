package com.aks.yummy.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;

public record ProductRequest (

        @JsonProperty("product_name")
        String name,

        @JsonProperty("price")
        Double price


    )
            {

}
