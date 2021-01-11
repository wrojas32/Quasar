package com.mercadolibre.quasar.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Position {
    private String x;
    private String y;

}
