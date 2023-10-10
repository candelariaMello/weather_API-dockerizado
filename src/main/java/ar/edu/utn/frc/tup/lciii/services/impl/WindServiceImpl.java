package ar.edu.utn.frc.tup.lciii.services.impl;

import ar.edu.utn.frc.tup.lciii.clients.wind.WindRestClient;
import ar.edu.utn.frc.tup.lciii.clients.wind.dtos.WindClientResponse;
import ar.edu.utn.frc.tup.lciii.services.WindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WindServiceImpl implements WindService {
    @Autowired
    private WindRestClient windRestClient;

    @Override
    public String getWind(Long id, String datetime) {
        WindClientResponse windClientResponse = windRestClient.getWind(id,datetime);
        String response = windClientResponse.speed()+" km/h from ";
        if(windClientResponse.direction()==0){
            response+="North";
        }
        else if (windClientResponse.direction()==90){
            response+="East";
        }
        else if(windClientResponse.direction()==180){
            response+="South";
        }
        else response+="West";
        return response;
    }
}
