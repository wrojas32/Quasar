package com.mercadolibre.quasar.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Satellite {
    private String name;
    private float distance;
    private String[] message;
}
