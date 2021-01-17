package com.mercadolibre.quasar.service;

import com.mercadolibre.quasar.domain.LocationResponse;
import com.mercadolibre.quasar.domain.Satellite;
import com.mercadolibre.quasar.domain.SatelliteEntity;
import com.mercadolibre.quasar.domain.SatellitesRequest;

public interface LocationService {
    LocationResponse getLocationAndMessage(SatellitesRequest satellitesRequest);
    SatelliteEntity saveSatellite(Satellite satellite);
    LocationResponse getLocationAndMessage();
}
