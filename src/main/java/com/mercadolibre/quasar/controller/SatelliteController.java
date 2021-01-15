package com.mercadolibre.quasar.controller;

import com.mercadolibre.quasar.domain.LocationResponse;
import com.mercadolibre.quasar.domain.Satellite;
import com.mercadolibre.quasar.domain.SatelliteEntity;
import com.mercadolibre.quasar.service.LocationService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/topsecret_split")
public class SatelliteController {

    @Autowired
    private LocationService locationService;

    @ApiOperation(value = "Save data received in particular satellite")
    @PostMapping(path = "/{satellite_name}")
    public ResponseEntity<SatelliteEntity> saveSatellite(@PathVariable(name = "satellite_name") String satelliteName,
                                                         @RequestBody Satellite satellite){
        satellite.setName(satelliteName);
        return ResponseEntity.ok(locationService.saveSatellite(satellite));
    }

    @ApiOperation(value = "Get location and message from spaceship")
    @GetMapping
    public ResponseEntity<LocationResponse> geLocationAndMessage(){
        return ResponseEntity.ok(locationService.getLocationAndMessage());
    }
}
