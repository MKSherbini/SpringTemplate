package com.skull.backend.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChickenDTO {
    @NotEmpty
    private String chickenName;
    private int size;
}

