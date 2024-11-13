package br.com.barberbook.server.controller;

import br.com.barberbook.server.dto.request.ScheduleRequestDTO;
import br.com.barberbook.server.dto.response.ScheduleResponseDTO;
import br.com.barberbook.server.dto.update.ScheduleUpdateDTO;
import br.com.barberbook.server.model.BarbershopModel;
import br.com.barberbook.server.model.PackageTypeModel;
import br.com.barberbook.server.model.ScheduleModel;
import br.com.barberbook.server.model.UserModel;
import br.com.barberbook.server.repository.BarbershopRepository;
import br.com.barberbook.server.repository.PackageTypeRepository;
import br.com.barberbook.server.repository.UserRepository;
import br.com.barberbook.server.service.ScheduleService;
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
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BarbershopRepository barbershopRepository;

    @Autowired
    private PackageTypeRepository packageTypeRepository;

    @PostMapping("/schedule")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ScheduleResponseDTO> createSchedule(@RequestBody @Valid ScheduleRequestDTO schedule) throws Exception {
        try {
            UUID userId = schedule.user().getId();
            UserModel userModel = userRepository.findById(userId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "The user is not found."));
            UUID barbershopId = schedule.barberShop().getId();
            BarbershopModel barbershopModel = barbershopRepository.findById(barbershopId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "The barbershop is not found."));
            Integer packageTypeId = schedule.packageType().getId();
            PackageTypeModel packageTypeModel = packageTypeRepository.findById(packageTypeId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "The package type is not found."));
            return new ResponseEntity<>(scheduleService.createSchedule(schedule, userModel, barbershopModel, packageTypeModel), HttpStatus.CREATED);
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "The schedule cannot be created.");
        }
    }

    @GetMapping("/schedule")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ScheduleResponseDTO> readSchedule(@RequestParam @Valid UUID id) throws Exception {
        try {
            return new ResponseEntity<>(scheduleService.readSchedule(id), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "The schedule cannot be found.");
        }
    }

    @PutMapping("/schedule")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ScheduleModel> updateSchedule(@RequestBody @Valid ScheduleUpdateDTO scheduleUpdateDTO) throws Exception {
        try {
            ScheduleModel updateSchedule = scheduleService.updateSchedule(scheduleUpdateDTO);
            return new ResponseEntity<>(updateSchedule, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "The schedule cannot be updated.");
        }
    }

    @DeleteMapping("/schedule")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSchedule(UUID id) throws Exception {
        try {
            scheduleService.deleteSchedule(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "The schedule cannot be deleted.");
        }
    }

}
