package ar.edu.utn.frc.tup.lciii.dtos.common;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    @Email @NotNull
    private String email;
    @NotNull
    private String temperature_unit;
}
