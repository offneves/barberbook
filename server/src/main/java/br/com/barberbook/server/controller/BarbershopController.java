package br.com.barberbook.server.controller;

import br.com.barberbook.server.dto.request.BarbershopRequestDTO;
import br.com.barberbook.server.dto.response.BarbershopResponseDTO;
import br.com.barberbook.server.model.BarbershopModel;
import br.com.barberbook.server.service.BarbershopService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.UUID;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BarbershopController {

    @Autowired
    private BarbershopService barbershopService;

    @PostMapping("/barbershop")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<BarbershopResponseDTO> createBarbershop(@RequestBody @Valid BarbershopRequestDTO barbershop) throws Exception {
        try {
            return new ResponseEntity<>(barbershopService.createBarbershop(barbershop), HttpStatus.CREATED);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "The barbershop cannot be created.");
        }
    }

    @GetMapping("/barbershop")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<BarbershopResponseDTO> readBarbershop(@RequestParam UUID id) throws Exception {
        try {
            return new ResponseEntity<>(barbershopService.readBarbershop(id), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "The barbershop cannot be found.");
        }
    }

    @PutMapping("/barbershop")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<BarbershopModel> updateBarbershop(@RequestBody @Valid BarbershopModel barbershop) throws Exception {
        try {
            return new ResponseEntity<>(barbershopService.updateBarbershop(barbershop), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "The barbershop cannot be updated.");
        }
    }

    @DeleteMapping("/barbershop")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBarbershop(UUID id) throws Exception {
        try {
            barbershopService.deleteBarbershop(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "The barbershop cannot be deleted.");
        }
    }

}
