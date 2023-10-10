package ar.edu.utn.frc.tup.lciii.controllers;

import ar.edu.utn.frc.tup.lciii.clients.locations.dtos.LocationClientResponse;
import ar.edu.utn.frc.tup.lciii.dtos.common.UserDTO;
import ar.edu.utn.frc.tup.lciii.models.LocationInformation;
import ar.edu.utn.frc.tup.lciii.models.User;
import ar.edu.utn.frc.tup.lciii.services.LocationService;
import ar.edu.utn.frc.tup.lciii.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("weather")
@CrossOrigin(origins = "*")
public class WeatherController {
    @Autowired
    private UserService userService;
    @PostMapping("/subscribe")
    private ResponseEntity<User> login(@RequestBody @Valid UserDTO user){
        return ResponseEntity.ok(userService.newUser(user));
    }
    @Autowired
    private LocationService locationService;
    @GetMapping("/locations/location/{id}/{datetime}/{client_id}/{client_secret}")
    private ResponseEntity<LocationInformation> getLocation(@PathVariable Long id, @PathVariable Long client_id, @PathVariable Long client_secret, @PathVariable String datetime){
        return ResponseEntity.ok(locationService.getLocation(id,client_id,client_secret,datetime));
    }
    @GetMapping("/locations/{client_id}/{client_secret}")
    private ResponseEntity<List<LocationClientResponse>> getLocations(@PathVariable Long client_id,@PathVariable Long client_secret){
        return ResponseEntity.ok(locationService.getLocations(client_id,client_secret));
    }
}
