package com.mercadolibre.quasar.controller;

import com.mercadolibre.quasar.domain.LocationResponse;
import com.mercadolibre.quasar.domain.SatellitesRequest;
import com.mercadolibre.quasar.service.LocationService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/topsecret")
public class LocationController {

    @Autowired
    private LocationService locationService;

    @ApiOperation(value = "Save satellites information and get location and message from spaceship")
    @PostMapping
    public ResponseEntity<LocationResponse> getLocationAndMessage(@RequestBody SatellitesRequest satellitesRequest){
        return ResponseEntity.ok(locationService.getLocationAndMessage(satellitesRequest));
    }
}
