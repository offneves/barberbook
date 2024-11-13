package br.com.barberbook.server.dto.update;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;


public record ScheduleUpdateDTO(

        UUID id,
        UUID userId,
        UUID barberShopId,
        Integer packageTypeId,
        LocalDate date,
        BigDecimal price,
        String payment,
        String coupon

) {
}
