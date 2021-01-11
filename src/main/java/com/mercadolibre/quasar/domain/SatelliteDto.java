package com.mercadolibre.quasar.domain;

import lombok.Data;

@Data
public class SatelliteDto {
    private float kenobiDistance;
    private float skywalkerDistance;
    private float satoDistance;
    String[][] messages;
}
