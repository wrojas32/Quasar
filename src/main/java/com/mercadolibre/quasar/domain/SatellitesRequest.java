package com.mercadolibre.quasar.domain;

import lombok.Data;

import java.util.List;

@Data
public class SatellitesRequest {
    private List<Satellite> satellites;
}
