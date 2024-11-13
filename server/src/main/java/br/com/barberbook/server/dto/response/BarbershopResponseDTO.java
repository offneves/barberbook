package br.com.barberbook.server.dto.response;

import br.com.barberbook.server.model.BarbershopModel;
import br.com.barberbook.server.model.PackageTypeModel;
import java.util.List;


public record BarbershopResponseDTO(

        String name,
        String phone,
        String address,
        String city,
        String state,
        List<PackageTypeModel> packageType,
        String businessHour,
        String media

) {

    public BarbershopResponseDTO(BarbershopModel barbershopModel) {
        this(
                barbershopModel.getName(),
                barbershopModel.getPhone(),
                barbershopModel.getAddress(),
                barbershopModel.getCity(),
                barbershopModel.getState(),
                barbershopModel.getPackageType(),
                barbershopModel.getBusinessHour(),
                barbershopModel.getMedia()
        );
    }

}
