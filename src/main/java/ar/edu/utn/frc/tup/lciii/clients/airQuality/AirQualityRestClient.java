package ar.edu.utn.frc.tup.lciii.clients.airQuality;

import ar.edu.utn.frc.tup.lciii.clients.airQuality.dtos.AirQualityClientResponse;
import ar.edu.utn.frc.tup.lciii.clients.wind.dtos.WindClientResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;
@Service
public class AirQualityRestClient {
    private static final String RESILIENCE4J_INSTANCE_NAME = "microTemperature";
    private static final String FALLBACK_METHOD = "fallback";
    String baseResourceUrl = "https://my-json-server.typicode.com/LCIV-2023/fake-weather/air_quality";
    @Autowired
    private RestTemplate restTemplate;

    //@CircuitBreaker(name = RESILIENCE4J_INSTANCE_NAME, fallbackMethod = FALLBACK_METHOD)
    public AirQualityClientResponse getAirQuality(Long location_id, String datetime) {
        ResponseEntity<AirQualityClientResponse[]> response = restTemplate.getForEntity
                (baseResourceUrl +"?location_id=" + location_id+"&created_at="+datetime, AirQualityClientResponse[].class);

        if (response.getStatusCode().is2xxSuccessful()) return Objects.requireNonNull(response.getBody())[0];
        throw new EntityNotFoundException("No se encontró la calidad de aire");
    }

    public ResponseEntity<String> fallback(Exception ex) {
        return ResponseEntity.status(503).body("El servicio de ubicaciones se encuentra desactivado temporalmente, intente luego");
    }
}
