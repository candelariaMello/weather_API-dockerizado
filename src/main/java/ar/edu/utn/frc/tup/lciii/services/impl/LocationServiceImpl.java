package ar.edu.utn.frc.tup.lciii.services.impl;

import ar.edu.utn.frc.tup.lciii.clients.locations.LocationRestClient;
import ar.edu.utn.frc.tup.lciii.clients.locations.dtos.LocationClientResponse;
import ar.edu.utn.frc.tup.lciii.models.Location;
import ar.edu.utn.frc.tup.lciii.models.LocationInformation;
import ar.edu.utn.frc.tup.lciii.models.User;
import ar.edu.utn.frc.tup.lciii.services.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.MethodNotAllowedException;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private LocationRestClient locationRestClient;
    @Autowired
    private UserService userService;
    @Autowired
    private TemperatureService temperatureService;
    @Autowired
    private WindService windService;
    @Autowired
    private AirQualityService airQualityService;
    @Autowired
    private CloudinessService cloudinessService;

    @Override
    public LocationInformation getLocation(Long id,Long client_id,Long secret,String datetime) {
        if(!userService.validUser(client_id,secret)){
            throw new ResponseStatusException(HttpStatusCode.valueOf(405),"No permitido");

        }
        User user=userService.getUser(client_id);
        LocationInformation locationInformation = new LocationInformation();
        locationInformation.setLocation(modelMapper.map(locationRestClient.getLocation(id), Location.class));
        locationInformation.setTemperature(temperatureService.getTemperature(id,user.getTemperature_unit(),datetime));
        locationInformation.setWind(windService.getWind(id,datetime));
        locationInformation.setAir_quality(airQualityService.getAirQuality(id,datetime));
        locationInformation.setCloudiness(cloudinessService.getCloudiness(id,datetime));
        return locationInformation;
    }

    @Override
    public List<LocationClientResponse> getLocations(Long client_id,Long secret) {
        if(userService.validUser(client_id,secret)){
            return locationRestClient.getLocations();
        }
        throw new ResponseStatusException(HttpStatusCode.valueOf(405),"No permitido");

    }
}
