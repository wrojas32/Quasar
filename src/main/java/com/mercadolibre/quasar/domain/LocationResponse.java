package com.mercadolibre.quasar.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LocationResponse {
    private Position position;
    private String message;
}
