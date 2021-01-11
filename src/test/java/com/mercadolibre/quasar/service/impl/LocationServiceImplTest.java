package com.mercadolibre.quasar.service.impl;

import com.mercadolibre.quasar.domain.LocationResponse;
import com.mercadolibre.quasar.domain.Satellite;
import com.mercadolibre.quasar.domain.SatellitesRequest;
import com.mercadolibre.quasar.exceptions.NotFoundException;
import com.mercadolibre.quasar.repository.SatelliteRepository;
import com.mercadolibre.quasar.service.LocationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
class LocationServiceImplTest {

    @Autowired
    private LocationService locationService;

    private SatelliteRepository satelliteRepository;

    @Test
    void getLocationAndMessage() {

        SatellitesRequest satellitesRequest = new SatellitesRequest();
        satellitesRequest.setSatellites(getSatellites());

        LocationResponse locationResponse = locationService.getLocationAndMessage(satellitesRequest);
        assertEquals(locationResponse.getPosition().getX(), "1,0");
        assertEquals(locationResponse.getPosition().getY(), "0,0");
        assertEquals(locationResponse.getMessage(), "este es un mensaje secreto");
    }

    @Test
    void testGetLocationAndMessage() {
        getSatellites().forEach(satellite -> locationService.saveSatellite(satellite));
        LocationResponse locationResponse = locationService.getLocationAndMessage();
        assertEquals(locationResponse.getPosition().getX(), "1,0");
        assertEquals(locationResponse.getPosition().getY(), "0,0");
        assertEquals(locationResponse.getMessage(), "este es un mensaje secreto");

    }

    @Test
    public void testExpectedNotFoundException(){
        Assertions.assertThrows(NotFoundException.class, ()->{
            getWrongSatellites().forEach(satellite -> locationService.saveSatellite(satellite));
            locationService.getLocationAndMessage();
        });
    }

    private List<Satellite> getSatellites(){
        return new ArrayList<>() {{
            add(Satellite.builder().name("kenobi").distance(2).message(new String[] {"este", "", "", "mensaje", ""}).build());
            add(Satellite.builder().name("skywalker").distance(2).message(new String[] {"", "es", "", "", "secreto"}).build());
            add(Satellite.builder().name("sato").distance(3).message(new String[] {"este", "", "un", "", ""}).build());
        }};
    }

    private List<Satellite> getWrongSatellites(){
        return new ArrayList<>() {{
            add(Satellite.builder().name("kenobi").distance(20).message(new String[] {"este", "", "", "mensaje", ""}).build());
            add(Satellite.builder().name("skywalker").distance(50).message(new String[] {"", "es", "", "", "secreto"}).build());
            add(Satellite.builder().name("sato").distance(80).message(new String[] {"este", "", "un", "", ""}).build());
        }};
    }
}