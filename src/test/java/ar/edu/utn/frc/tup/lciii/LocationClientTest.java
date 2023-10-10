package ar.edu.utn.frc.tup.lciii;


import ar.edu.utn.frc.tup.lciii.clients.locations.LocationRestClient;
import ar.edu.utn.frc.tup.lciii.clients.locations.dtos.LocationClientResponse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
public class LocationClientTest {
    @MockBean
    private RestTemplate restTemplate;

    @SpyBean
    private LocationRestClient locationRestClient;


    @Test
    public void testGetLocation_Successful() {
        LocationClientResponse expectedResponse = new LocationClientResponse(1L, "LocationName", 12.34, 56.78);

        ResponseEntity<LocationClientResponse[]> responseEntity = new ResponseEntity<>(
                new LocationClientResponse[]{expectedResponse}, HttpStatus.OK);

        when(restTemplate.getForEntity(anyString(), eq(LocationClientResponse[].class)))
                .thenReturn(responseEntity);

        LocationClientResponse actualResponse = locationRestClient.getLocation(1L);

        assertEquals(expectedResponse, actualResponse);
    }
}

