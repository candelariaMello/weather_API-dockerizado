package ar.edu.utn.frc.tup.lciii.services;

import ar.edu.utn.frc.tup.lciii.clients.locations.dtos.LocationClientResponse;
import ar.edu.utn.frc.tup.lciii.models.LocationInformation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LocationService {
    LocationInformation getLocation(Long id, Long client_id, Long secret, String datetime);

    List<LocationClientResponse> getLocations(Long client_id,Long secret);
}
