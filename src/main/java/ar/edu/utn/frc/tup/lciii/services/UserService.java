package ar.edu.utn.frc.tup.lciii.services;

import ar.edu.utn.frc.tup.lciii.dtos.common.UserDTO;
import ar.edu.utn.frc.tup.lciii.models.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User newUser(UserDTO user);

    boolean validUser(Long clientId, Long secret);

    User getUser(Long clientId);
}
