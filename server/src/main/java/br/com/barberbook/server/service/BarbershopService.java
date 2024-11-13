package br.com.barberbook.server.service;

import br.com.barberbook.server.dto.request.BarbershopRequestDTO;
import br.com.barberbook.server.dto.response.BarbershopResponseDTO;
import br.com.barberbook.server.model.BarbershopModel;
import br.com.barberbook.server.repository.BarbershopRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class BarbershopService {

    @Autowired
    private BarbershopRepository barbershopRepository;

    public BarbershopResponseDTO createBarbershop(BarbershopRequestDTO barbershopRequestDTO) {
        BarbershopModel barbershop = new BarbershopModel();
        BeanUtils.copyProperties(barbershopRequestDTO, barbershop);
        barbershop = barbershopRepository.save(barbershop);
        return new BarbershopResponseDTO(barbershop);
    }

    public BarbershopResponseDTO readBarbershop(UUID id) {
        Optional<BarbershopModel> barbershopOptional = barbershopRepository.findById(id);
        if (barbershopOptional.isPresent()) {
            return new BarbershopResponseDTO(barbershopOptional.get());
        } else {
            throw new RuntimeException("The barbershop with id " + id + " not found");
        }
    }

    public List<BarbershopResponseDTO> readAllBarbershop() {
        return barbershopRepository
                .findAll()
                .stream()
                .map(BarbershopResponseDTO::new)
                .toList();
    }

    public BarbershopModel updateBarbershop(BarbershopModel barbershopModel) {
        Optional<BarbershopModel> barbershopOptional = barbershopRepository.findById(barbershopModel.getId());
        if (barbershopOptional.isPresent()) {
            return barbershopRepository.save(barbershopModel);
        } else {
            throw new RuntimeException("The barbershop with id " + barbershopModel.getId() + " does not exist.");
        }
    }

    public void deleteBarbershop(UUID id) {
        Optional<BarbershopModel> barbershopOptional = barbershopRepository.findById(id);
        if (barbershopOptional.isPresent()) {
            barbershopRepository.delete(barbershopOptional.get());
        } else {
            throw new RuntimeException("The barbershop with id " + id + " not found");
        }
    }

}
