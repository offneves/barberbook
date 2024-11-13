package br.com.barberbook.server.dto.request;

import br.com.barberbook.server.util.UserRoleEnum;
import jakarta.validation.constraints.*;
import java.time.LocalDate;


public record UserRequestDTO(

        @NotBlank(message = "The username is mandatory.")
        String username,
        @NotBlank(message = "The password is mandatory.")
        @Size(min = 6, max = 20, message = "The password must contain between 6 and 20 characters.")
        @Pattern(
                regexp = "^(?=.*[A-Z])(?=.*[!@#$%^&*()_+\\-={};:'\",.<>?]).*$",
                message = "The password must contain at least one uppercase letter and one symbol."
        )
        String password,
        @NotBlank(message = "The name is mandatory.")
        String name,
        @NotBlank(message = "The email is mandatory.")
        @Email(message = "The email format is invalid.")
        String email,
        @NotBlank(message = "The phone number is mandatory.")
        @Pattern(
                regexp = "\\d{2}-\\d{5}-\\d{4}",
                message = "The phone number must follow the pattern 12-99999-9999."
        )
        String phone,
        @Past(message = "The birthDate is invalid.")
        LocalDate birthDate,
        String address,
        String city,
        String state,
        @NotNull(message = "The role is mandatory.")
        UserRoleEnum role

) {
}
