package br.com.barberbook.server.dto.request;

import br.com.barberbook.server.model.BarbershopModel;
import br.com.barberbook.server.model.PackageTypeModel;
import br.com.barberbook.server.model.UserModel;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;


public record ScheduleRequestDTO(

        UserModel user,
        BarbershopModel barberShop,
        PackageTypeModel packageType,
        @NotNull(message = "The date is mandatory.")
        @Future(message = "The date is invalid.")
        LocalDate date,
        @NotNull(message = "The price is mandatory.")
        BigDecimal price,
        @NotNull(message = "The payment is mandatory.")
        String payment,
        String coupon

) {
}
