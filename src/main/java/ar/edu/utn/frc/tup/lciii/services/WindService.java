package ar.edu.utn.frc.tup.lciii.services;

import org.springframework.stereotype.Service;

@Service
public interface WindService {

    String getWind(Long id, String datetime);
}
