package ar.edu.utn.frc.tup.lciii.services;

import ar.edu.utn.frc.tup.lciii.models.Cloudiness;
import org.springframework.stereotype.Service;

@Service
public interface CloudinessService {
    Cloudiness getCloudiness(Long id, String datetime);
}
