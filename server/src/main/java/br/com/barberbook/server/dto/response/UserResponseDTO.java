package br.com.barberbook.server.dto.response;

import br.com.barberbook.server.model.UserModel;
import java.time.LocalDate;


public record UserResponseDTO(

        String username,
        String name,
        String email,
        String phone,
        LocalDate birthDate,
        String address,
        String city,
        String state
) {

    public UserResponseDTO(UserModel userModel) {
        this(
                userModel.getUsername(),
                userModel.getName(),
                userModel.getEmail(),
                userModel.getPhone(),
                userModel.getBirthDate(),
                userModel.getAddress(),
                userModel.getCity(),
                userModel.getState()
        );
    }

}
