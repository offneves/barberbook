package br.com.barberbook.server.dto.update;

import java.math.BigDecimal;
import java.util.UUID;


public record PackageTypeUpdateDTO(

        Integer id,
        UUID barbershopId,
        String typeName,
        BigDecimal price

) {
}
