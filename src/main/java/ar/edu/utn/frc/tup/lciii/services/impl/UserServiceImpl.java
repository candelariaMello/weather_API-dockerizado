package ar.edu.utn.frc.tup.lciii.services.impl;

import ar.edu.utn.frc.tup.lciii.dtos.common.UserDTO;
import ar.edu.utn.frc.tup.lciii.entities.UserEntity;
import ar.edu.utn.frc.tup.lciii.models.User;
import ar.edu.utn.frc.tup.lciii.repositories.UserJpaRepository;
import ar.edu.utn.frc.tup.lciii.services.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserJpaRepository userJpaRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public User newUser(UserDTO user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(user.getEmail());
        userEntity.setTemperature_unit(user.getTemperature_unit());
        userEntity.setSecret(1234L);
        UserEntity userEntitySaved=userJpaRepository.save(userEntity);
        if(userEntitySaved.getId()==null){
            throw new RuntimeException("Error al crear al usuario");
        }

        return modelMapper.map(userEntitySaved,User.class);
    }

    @Override
    public boolean validUser(Long clientId, Long secret) {
        Optional<UserEntity> userEntity = userJpaRepository.findByIdAndSecret(clientId,secret);
        return userEntity.isPresent();
    }

    @Override
    public User getUser(Long clientId) {
        UserEntity userEntity = userJpaRepository.getReferenceById(clientId);
        if(userEntity.getTemperature_unit()!=null){
            return modelMapper.map(userEntity,User.class);
        }
        throw new EntityNotFoundException("Usuario no encontrado");
    }
}
