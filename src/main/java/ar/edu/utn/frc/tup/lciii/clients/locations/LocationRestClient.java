package ar.edu.utn.frc.tup.lciii.clients.locations;

import ar.edu.utn.frc.tup.lciii.clients.locations.dtos.LocationClientResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@Service
public class LocationRestClient {

    private static final String RESILIENCE4J_INSTANCE_NAME = "locations";
    private static final String FALLBACK_METHOD_SPECIFIC = "fallbackSpecificLocation";
    private static final String FALLBACK_METHOD = "fallback";
    String baseResourceUrl = "https://my-json-server.typicode.com/LCIV-2023/fake-weather/location";
    private int counter=0;
    @Autowired
    private RestTemplate restTemplate;

    @CircuitBreaker(name = RESILIENCE4J_INSTANCE_NAME, fallbackMethod = FALLBACK_METHOD_SPECIFIC)
    public LocationClientResponse getLocation(Long location_id) {
        counter++;

        ResponseEntity<LocationClientResponse[]> response = restTemplate.getForEntity
                (baseResourceUrl +"?id=" + location_id, LocationClientResponse[].class);

        if (response.getStatusCode().is2xxSuccessful() && (counter<3 || counter>6)) return Objects.requireNonNull(response.getBody())[0];
        throw new EntityNotFoundException("No se encontró la ubicación");
    }

    public LocationClientResponse fallbackSpecificLocation(Exception ex) {
        counter++;
        throw new HttpClientErrorException(HttpStatusCode.valueOf(503),"El servicio de ubicaciones se encuentra desactivado temporalmente, intente luego");
    }
    public List<LocationClientResponse> fallback(Exception ex) {
        counter++;
        throw new HttpClientErrorException(HttpStatusCode.valueOf(503),"El servicio de ubicaciones se encuentra desactivado temporalmente, intente luego");
    }
    @CircuitBreaker(name = RESILIENCE4J_INSTANCE_NAME, fallbackMethod = FALLBACK_METHOD)
    public List<LocationClientResponse> getLocations() {
        counter++;
        ResponseEntity<LocationClientResponse[]> response = restTemplate.getForEntity
                (baseResourceUrl, LocationClientResponse[].class);

        if (response.getStatusCode().is2xxSuccessful()&& (counter<3 || counter>6)) return List.of(Objects.requireNonNull(response.getBody()));
        throw new EntityNotFoundException("No se encontraron ubicaciones");
    }
}
