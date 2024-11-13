package br.com.barberbook.server.controller;

import br.com.barberbook.server.dto.request.PackageTypeRequestDTO;
import br.com.barberbook.server.dto.response.PackageTypeResponseDTO;
import br.com.barberbook.server.dto.update.PackageTypeUpdateDTO;
import br.com.barberbook.server.model.BarbershopModel;
import br.com.barberbook.server.model.PackageTypeModel;
import br.com.barberbook.server.repository.BarbershopRepository;
import br.com.barberbook.server.service.PackageTypeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PackageTypeController {

    @Autowired
    private PackageTypeService packageTypeService;

    @Autowired
    private BarbershopRepository barbershopRepository;

    @PostMapping("/packageType")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PackageTypeResponseDTO> createPackage(@RequestBody @Valid PackageTypeRequestDTO packageType) throws Exception {
        try {
            UUID barbershopId = packageType.barbershop().getId();
            BarbershopModel barbershopModel = barbershopRepository.findById(barbershopId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "The barbershop is not found."));
            return new ResponseEntity<>(packageTypeService.createPackageType(packageType, barbershopModel), HttpStatus.CREATED);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "The packageType cannot be created.");
        }
    }

    @GetMapping("/packageType")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PackageTypeResponseDTO> readPackage(@RequestParam @Valid Integer id) throws Exception {
        try {
            return new ResponseEntity<>(packageTypeService.readPackageType(id), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "The packageType cannot be found.");
        }
    }

    @GetMapping("/packageTypeAll")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<PackageTypeResponseDTO>> readAllPackage() throws Exception {
        try {
            return new ResponseEntity<>(packageTypeService.readAllPackageTypes(), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "The all packageType cannot be found.");
        }
    }

    @PutMapping("/packageType")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PackageTypeModel> updatePackage(@RequestBody @Valid PackageTypeUpdateDTO packageTypeUpdateDTO) throws Exception {
        try {
            PackageTypeModel updatePackage = packageTypeService.updatePackageType(packageTypeUpdateDTO);
            return new ResponseEntity<>(updatePackage, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "The packageType cannot be updated.");
        }
    }

    @DeleteMapping("/packageType")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePackage(Integer id) throws Exception {
        try {
            packageTypeService.deletePackageType(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "The packageType cannot be deleted.");
        }
    }

}
