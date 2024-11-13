package br.com.barberbook.server.service;

import br.com.barberbook.server.dto.request.UserRequestDTO;
import br.com.barberbook.server.dto.response.UserResponseDTO;
import br.com.barberbook.server.model.UserModel;
import br.com.barberbook.server.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private boolean isEmailRegistered(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        UserModel user = new UserModel();
        BeanUtils.copyProperties(userRequestDTO, user);
        if (isEmailRegistered(user.getEmail())) {
            throw new IllegalArgumentException("The e-mail already registered.");
        }
        user = userRepository.save(user);
        return new UserResponseDTO(user);
    }

    public UserResponseDTO readUserById(UUID id) {
        Optional<UserModel> user = userRepository.findById(id);
        if (user.isPresent()) {
            return new UserResponseDTO(user.get());
        } else {
            throw new RuntimeException("The user with id " + id + " does not found.");
        }
    }

    public List<UserResponseDTO> readAllUsers() {
        return userRepository
                .findAll()
                .stream()
                .map(UserResponseDTO::new)
                .toList();
    }

    public UserModel updateUser(UserModel userModel) {
        Optional<UserModel> userOptional = userRepository.findById(userModel.getId());
        if (userOptional.isPresent()) {
            return userRepository.save(userModel);
        } else {
            throw new RuntimeException("The user with id " + userModel.getId() + " does not exist");
        }
    }

    public void deleteUser(UUID id) {
        Optional<UserModel> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            userRepository.delete(userOptional.get());
        } else {
            throw new RuntimeException("The user with id " + id + " not found");
        }
    }

}
