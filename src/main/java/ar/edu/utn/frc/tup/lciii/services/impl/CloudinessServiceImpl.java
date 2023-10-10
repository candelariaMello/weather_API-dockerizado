package ar.edu.utn.frc.tup.lciii.services.impl;

import ar.edu.utn.frc.tup.lciii.clients.cloudiness.CloudinessRestClient;
import ar.edu.utn.frc.tup.lciii.clients.cloudiness.dtos.CloudinessClientResponse;
import ar.edu.utn.frc.tup.lciii.models.Cloudiness;
import ar.edu.utn.frc.tup.lciii.services.CloudinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CloudinessServiceImpl implements CloudinessService {
    @Autowired
    private CloudinessRestClient cloudinessRestClient;
    @Override
    public Cloudiness getCloudiness(Long id, String datetime) {
        CloudinessClientResponse cloudinessClientResponse = cloudinessRestClient.getCloudiness(id,datetime);
        Cloudiness cloudiness = new Cloudiness();
        Integer levelOfCloudiness = cloudinessClientResponse.cloudiness();
        cloudiness.setIndex(levelOfCloudiness);
        if(levelOfCloudiness==0){
            cloudiness.setDescription("Clear sky");
        }else if(levelOfCloudiness>0 & levelOfCloudiness<=3){
            cloudiness.setDescription("Few clouds");
        }else if(levelOfCloudiness>3 & levelOfCloudiness<=6){
            cloudiness.setDescription("Sky half cloudy");
        }else if(levelOfCloudiness>6 & levelOfCloudiness<=8){
            cloudiness.setDescription("Sky completely cloudy");
        }

        return cloudiness;
    }
}
