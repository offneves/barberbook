package br.com.barberbook.server.dto.response;

import br.com.barberbook.server.model.PackageTypeModel;
import java.math.BigDecimal;
import java.util.UUID;


public record PackageTypeResponseDTO(

        UUID barbershopId,
        String typeName,
        BigDecimal price

) {

    public PackageTypeResponseDTO(PackageTypeModel packageType) {
        this(
                packageType.getBarbershop().getId(),
                packageType.getTypeName(),
                packageType.getPrice()
        );
    }

}
