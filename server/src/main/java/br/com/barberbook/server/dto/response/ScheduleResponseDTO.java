package br.com.barberbook.server.dto.response;

import br.com.barberbook.server.model.BarbershopModel;
import br.com.barberbook.server.model.PackageTypeModel;
import br.com.barberbook.server.model.ScheduleModel;
import br.com.barberbook.server.model.UserModel;
import java.math.BigDecimal;
import java.time.LocalDate;


public record ScheduleResponseDTO(

        UserModel user,
        BarbershopModel barberShop,
        PackageTypeModel packageType,
        LocalDate date,
        BigDecimal price,
        String payment,
        String coupon

) {

    public ScheduleResponseDTO(ScheduleModel scheduleModel) {
        this(
                scheduleModel.getUser(),
                scheduleModel.getBarberShop(),
                scheduleModel.getPackageType(),
                scheduleModel.getDate(),
                scheduleModel.getPrice(),
                scheduleModel.getPayment(),
                scheduleModel.getCoupon()
        );
    }

}
