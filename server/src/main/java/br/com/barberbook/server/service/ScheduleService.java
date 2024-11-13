package br.com.barberbook.server.service;

import br.com.barberbook.server.dto.request.ScheduleRequestDTO;
import br.com.barberbook.server.dto.response.ScheduleResponseDTO;
import br.com.barberbook.server.dto.update.ScheduleUpdateDTO;
import br.com.barberbook.server.model.BarbershopModel;
import br.com.barberbook.server.model.PackageTypeModel;
import br.com.barberbook.server.model.ScheduleModel;
import br.com.barberbook.server.model.UserModel;
import br.com.barberbook.server.repository.BarbershopRepository;
import br.com.barberbook.server.repository.PackageTypeRepository;
import br.com.barberbook.server.repository.ScheduleRepository;
import br.com.barberbook.server.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BarbershopRepository barbershopRepository;
    @Autowired
    private PackageTypeRepository packageTypeRepository;

    public ScheduleResponseDTO createSchedule(ScheduleRequestDTO scheduleRequestDTO, UserModel userModel, BarbershopModel barbershopModel, PackageTypeModel packageTypeModel) {
        ScheduleModel schedule = new ScheduleModel();
        BeanUtils.copyProperties(scheduleRequestDTO, schedule);
        schedule.setUser(userModel);
        schedule.setBarberShop(barbershopModel);
        schedule.setPackageType(packageTypeModel);
        scheduleRepository.save(schedule);
        return new ScheduleResponseDTO(schedule);
    }

    public ScheduleResponseDTO readSchedule(UUID id) {
        Optional<ScheduleModel> schedule = scheduleRepository.findById(id);
        if (schedule.isPresent()) {
            return new ScheduleResponseDTO(schedule.get());
        } else {
            throw new RuntimeException("The schedule with id " + id + " not found");
        }
    }

    public List<ScheduleResponseDTO> readAllSchedules() {
        return scheduleRepository
                .findAll()
                .stream()
                .map(ScheduleResponseDTO::new)
                .toList();
    }

    public ScheduleModel updateSchedule(ScheduleUpdateDTO scheduleUpdateDTO) {
        Optional<ScheduleModel> scheduleOptional = Optional.ofNullable(scheduleRepository.findById(scheduleUpdateDTO.id())
                .orElseThrow(() -> new RuntimeException("The schedule with id " + scheduleUpdateDTO.id() + " does not exist.")));
        UserModel userModel = userRepository.findById(scheduleUpdateDTO.userId())
                .orElseThrow(() -> new RuntimeException("The user with id " + scheduleUpdateDTO.userId() + " does not exist."));
        BarbershopModel barbershopModel = barbershopRepository.findById(scheduleUpdateDTO.barberShopId())
                .orElseThrow(() -> new RuntimeException("The barbershop with id " + scheduleUpdateDTO.barberShopId() + " does not exist."));
        PackageTypeModel packageTypeModel = packageTypeRepository.findById(scheduleUpdateDTO.packageTypeId())
                .orElseThrow(() -> new RuntimeException("The package type with id " + scheduleUpdateDTO.packageTypeId() + " does not exist."));
        scheduleOptional.get().setUser(userModel);
        scheduleOptional.get().setBarberShop(barbershopModel);
        scheduleOptional.get().setPackageType(packageTypeModel);
        scheduleOptional.get().setDate(scheduleUpdateDTO.date());
        scheduleOptional.get().setPrice(scheduleUpdateDTO.price());
        scheduleOptional.get().setPayment(scheduleUpdateDTO.payment());
        scheduleOptional.get().setCoupon(scheduleUpdateDTO.coupon());
        return scheduleRepository.save(scheduleOptional.get());
    }

    public void deleteSchedule(UUID id) {
        Optional<ScheduleModel> scheduleOptional = scheduleRepository.findById(id);
        if (scheduleOptional.isPresent()) {
            scheduleRepository.delete(scheduleOptional.get());
        } else {
            throw new RuntimeException("The schedule with id " + id + " not found.");
        }
    }

}