package br.com.barberbook.server.dto.request;

import br.com.barberbook.server.util.UserRoleEnum;


public record RegisterRequestDTO(

        String login,
        String password,
        UserRoleEnum role

) {
}
