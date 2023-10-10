package ar.edu.utn.frc.tup.lciii.services;

import ar.edu.utn.frc.tup.lciii.models.Temperature;
import org.springframework.stereotype.Service;

@Service
public interface TemperatureService {
    Temperature getTemperature(Long id, String temperatureUnit,String datetime);
}
