package ar.edu.utn.frc.tup.lciii.services.impl;

import ar.edu.utn.frc.tup.lciii.clients.airQuality.AirQualityRestClient;
import ar.edu.utn.frc.tup.lciii.clients.airQuality.dtos.AirQualityClientResponse;
import ar.edu.utn.frc.tup.lciii.models.AirQuality;
import ar.edu.utn.frc.tup.lciii.services.AirQualityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AirQualityServiceImpl implements AirQualityService {
    @Autowired
    private AirQualityRestClient airQualityRestClient;

    @Override
    public AirQuality getAirQuality(Long id, String datetime) {
        AirQualityClientResponse airQualityClientResponse = airQualityRestClient.getAirQuality(id,datetime);
        Integer quality = airQualityClientResponse.quality();
        AirQuality airQuality = new AirQuality();
        if(quality==0 & quality<=50){
            airQuality.setDescription("Good");
        }else if(quality>50 & quality<=100){
            airQuality.setDescription("Moderate");
        }else if(quality>100 & quality<=150){
            airQuality.setDescription("Unhealthy for Sensitive Groups");
        }else if(quality>150 & quality<=200){
            airQuality.setDescription("Unhealthy");
        }else if(quality>200 & quality<=300){
            airQuality.setDescription("Very Unhealthy");
        }else if(quality>300 & quality<=500){
            airQuality.setDescription("Hazardous");
        }
        airQuality.setIndex(quality);

        return airQuality;
    }
}
