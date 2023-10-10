package ar.edu.utn.frc.tup.lciii.clients.locations.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationClientResponse {
    private Long id; private String name; private Double latitude; private Double longitude;

}
