package com.mercadolibre.quasar.service.impl;

import com.mercadolibre.quasar.constants.SatelliteEnum;
import com.mercadolibre.quasar.domain.SatelliteDto;
import com.mercadolibre.quasar.domain.Satellite;
import com.mercadolibre.quasar.exceptions.NotFoundException;
import com.mercadolibre.quasar.repository.SatelliteRepository;
import com.mercadolibre.quasar.domain.LocationResponse;
import com.mercadolibre.quasar.domain.Position;
import com.mercadolibre.quasar.domain.SatelliteEntity;
import com.mercadolibre.quasar.domain.SatellitesRequest;
import com.mercadolibre.quasar.service.LocationService;
import com.mercadolibre.quasar.util.Utilities;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    private SatelliteRepository satelliteRepository;

    @Value("${satellite.kenobi.position.x}")
    private Double kenobiX;

    @Value("${satellite.kenobi.position.y}")
    private Double kenobiY;

    @Value("${satellite.skywalker.position.x}")
    private Double skywalkerX;

    @Value("${satellite.skywalker.position.y}")
    private Double skywalkerY;

    @Value("${satellite.sato.position.x}")
    private Double satoX;

    @Value("${satellite.sato.position.y}")
    private Double satoY;

    @Override
    public LocationResponse getLocationAndMessage(SatellitesRequest satellitesRequest) {
        SatelliteDto satelliteDto = getMessagesAndDistances(satellitesRequest.getSatellites());
        satellitesRequest.getSatellites().forEach(this::saveSatellite);
        String[] position = getLocation(satelliteDto.getKenobiDistance(), satelliteDto.getSkywalkerDistance(),
            satelliteDto.getSatoDistance());
        validateFinalPosition(position);
        String finalMessage = String.join(" ", Utilities.getMessage(satelliteDto.getMessages()));

        return LocationResponse.builder().position(Position.builder().x(position[0]).y(position[1]).build())
            .message(finalMessage).build();
    }

    private String[] getLocation(float... distances){
        return Utilities.calculateThreeCircleIntersection(kenobiX, kenobiY, distances[0], skywalkerX, skywalkerY,
            distances[1], satoX, satoY,  distances[2]);
    }

    @Override
    public SatelliteEntity saveSatellite(Satellite satellite) {
        return satelliteRepository.save(SatelliteEntity.builder().distance(satellite.getDistance())
            .name(satellite.getName()).message(satellite.getMessage()).build());
    }

    @Override
    public LocationResponse getLocationAndMessage() {
        List<SatelliteEntity> satelliteList =  satelliteRepository.findAll();
        ModelMapper modelMapper = new ModelMapper();
        List<Satellite> satellites = satelliteList.stream().map(entity-> modelMapper.map(entity, Satellite.class))
            .collect(Collectors.toList());

        SatelliteDto satelliteDto = getMessagesAndDistances(satellites);

        String[] position = getLocation(satelliteDto.getKenobiDistance(), satelliteDto.getSkywalkerDistance(),
            satelliteDto.getSatoDistance());
        validateFinalPosition(position);
        String finalMessage = String.join(" ", Utilities.getMessage(satelliteDto.getMessages()));

        return LocationResponse.builder().position(Position.builder().x(position[0]).y(position[1]).build())
            .message(finalMessage).build();
    }

    private SatelliteDto getMessagesAndDistances(List<Satellite> satellitesList){
        SatelliteDto satelliteDto = new SatelliteDto();
        String[][] messages = new String[3][];
        satellitesList.forEach(s-> {
            if(s.getName().equals(SatelliteEnum.KENOBI.label)){
                satelliteDto.setKenobiDistance(s.getDistance());
                messages[0]= s.getMessage();
            }
            if(s.getName().equals(SatelliteEnum.SKYWALKER.label)){
                satelliteDto.setSkywalkerDistance(s.getDistance());
                messages[1]= s.getMessage();
            }
            if(s.getName().equals(SatelliteEnum.SATO.label)){
                satelliteDto.setSatoDistance(s.getDistance());
                messages[2]= s.getMessage();
            }
        });
        satelliteDto.setMessages(messages);
        return satelliteDto;
    }

    private void validateFinalPosition(String[] position){
        if(position[0] == null || position[1] == null){
            throw new NotFoundException("The information is incomplete to define the location");
        }
    }
}
