package br.com.barberbook.server.service;

import br.com.barberbook.server.dto.request.PackageTypeRequestDTO;
import br.com.barberbook.server.dto.response.PackageTypeResponseDTO;
import br.com.barberbook.server.dto.update.PackageTypeUpdateDTO;
import br.com.barberbook.server.model.BarbershopModel;
import br.com.barberbook.server.model.PackageTypeModel;
import br.com.barberbook.server.repository.BarbershopRepository;
import br.com.barberbook.server.repository.PackageTypeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class PackageTypeService {

    @Autowired
    private PackageTypeRepository packageTypeRepository;
    @Autowired
    private BarbershopRepository barbershopRepository;

    public PackageTypeResponseDTO createPackageType(PackageTypeRequestDTO packageTypeRequestDTO, BarbershopModel barbershopModel) {
        PackageTypeModel packageType = new PackageTypeModel();
        BeanUtils.copyProperties(packageTypeRequestDTO, packageType);
        packageType.setBarbershop(barbershopModel);
        packageTypeRepository.save(packageType);
        return new PackageTypeResponseDTO(packageType);
    }

    public PackageTypeResponseDTO readPackageType(Integer id) {
        Optional<PackageTypeModel> packageType = packageTypeRepository.findById(id);
        if (packageType.isPresent()) {
            return new PackageTypeResponseDTO(packageType.get());
        } else {
            throw new RuntimeException("The packageType with id " + id + " not found.");
        }
    }

    public List<PackageTypeResponseDTO> readAllPackageTypes() {
        return packageTypeRepository
                .findAll()
                .stream()
                .map(PackageTypeResponseDTO::new)
                .toList();
    }

    public PackageTypeModel updatePackageType(PackageTypeUpdateDTO packageTypeUpdateDTO) {
        Optional<PackageTypeModel> packageTypeModel = Optional.ofNullable(packageTypeRepository.findById(packageTypeUpdateDTO.id())
                .orElseThrow(() -> new RuntimeException("The packageType with id " + packageTypeUpdateDTO.id() + " does not exist.")));
        BarbershopModel barbershopModel = barbershopRepository.findById(packageTypeUpdateDTO.barbershopId())
                .orElseThrow(() -> new RuntimeException("The barbershop with id " + packageTypeUpdateDTO.barbershopId() + " does not exist."));
        packageTypeModel.get().setBarbershop(barbershopModel);
        packageTypeModel.get().setTypeName(packageTypeUpdateDTO.typeName());
        packageTypeModel.get().setPrice(packageTypeUpdateDTO.price());
        return packageTypeRepository.save(packageTypeModel.get());
    }

    public void deletePackageType(Integer id) {
        Optional<PackageTypeModel> packageType = packageTypeRepository.findById(id);
        if (packageType.isPresent()) {
            packageTypeRepository.deleteById(id);
        } else {
            throw new RuntimeException("The packageType with id " + id + " not found.");
        }
    }

}
