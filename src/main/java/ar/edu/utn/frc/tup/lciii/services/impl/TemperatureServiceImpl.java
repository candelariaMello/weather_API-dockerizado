package ar.edu.utn.frc.tup.lciii.services.impl;

import ar.edu.utn.frc.tup.lciii.clients.temperature.TemperatureRestClient;
import ar.edu.utn.frc.tup.lciii.clients.temperature.dtos.TemperatureClientResponse;
import ar.edu.utn.frc.tup.lciii.models.Temperature;
import ar.edu.utn.frc.tup.lciii.services.TemperatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class TemperatureServiceImpl implements TemperatureService {

    @Autowired
    private TemperatureRestClient temperatureRestClient;
    @Override
    public Temperature getTemperature(Long id, String temperatureUnit,String datetime) {
        TemperatureClientResponse temperatureClientResponse = temperatureRestClient.getTemperature(id,datetime);
        Temperature temperature = new Temperature(Double.valueOf(temperatureClientResponse.temperature()),temperatureClientResponse.unit());
        if(!Objects.equals(temperature.getUnit(), temperatureUnit)){
            temperature.setValue(temperature.getValue()*33.8);
            temperature.setUnit("F");
        }
        return temperature;
    }
}
