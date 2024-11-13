package br.com.barberbook.server.dto.request;

import br.com.barberbook.server.model.BarbershopModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;


public record PackageTypeRequestDTO(

        BarbershopModel barbershop,
        @NotBlank(message = "The typeName is mandatory.")
        String typeName,
        @NotNull(message = "The price is mandatory.")
        BigDecimal price

) {
}