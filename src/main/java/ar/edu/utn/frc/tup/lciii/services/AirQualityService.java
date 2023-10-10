package ar.edu.utn.frc.tup.lciii.services;

import ar.edu.utn.frc.tup.lciii.models.AirQuality;
import org.springframework.stereotype.Service;

@Service
public interface AirQualityService {

    AirQuality getAirQuality(Long id, String datetime);
}
