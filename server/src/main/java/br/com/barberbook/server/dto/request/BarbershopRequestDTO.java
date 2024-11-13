package br.com.barberbook.server.dto.request;

import br.com.barberbook.server.model.PackageTypeModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.util.List;


public record BarbershopRequestDTO(

        @NotBlank(message = "The name is mandatory.")
        String name,
        @NotBlank(message = "The phone number is mandatory.")
        @Pattern(
                regexp = "\\d{2}-\\d{5}-\\d{4}",
                message = "The phone number must follow the pattern 12-99999-9999."
        )
        String phone,
        @NotBlank(message = "The address is mandatory.")
        String address,
        @NotBlank(message = "The city is mandatory.")
        String city,
        @NotBlank(message = "The state is mandatory.")
        String state,
        List<PackageTypeModel> packageType,
        String businessHour,
        String media

) {
}
