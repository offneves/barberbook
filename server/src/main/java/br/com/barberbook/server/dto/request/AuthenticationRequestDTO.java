package br.com.barberbook.server.dto.request;


public record AuthenticationRequestDTO(

        String login,
        String password

) {
}
