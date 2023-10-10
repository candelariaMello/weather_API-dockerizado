package ar.edu.utn.frc.tup.lciii.clients.locations.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationsPost {
    private Long client_id;
    private Long client_secret;
}
