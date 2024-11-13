package br.com.barberbook.server.controller;

import br.com.barberbook.server.dto.request.UserRequestDTO;
import br.com.barberbook.server.dto.response.UserResponseDTO;
import br.com.barberbook.server.model.UserModel;
import br.com.barberbook.server.service.UserService;
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
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody @Valid UserRequestDTO user) throws Exception, IllegalArgumentException {
        try {
            return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "The user cannot be created.");
        }
    }

    @GetMapping("/user")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<UserResponseDTO> readUser(@RequestParam @Valid UUID id) throws Exception {
        try {
            return new ResponseEntity<>(userService.readUserById(id), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "The user cannot be found.");
        }
    }

    @PutMapping("/user")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<UserModel> updateUser(@RequestBody @Valid UserModel user) throws Exception {
        try {
            return new ResponseEntity<>(userService.updateUser(user), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "The user cannot be updated.");
        }
    }

    @DeleteMapping("/user")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@RequestParam @Valid UUID id) throws Exception {
        try {
            userService.deleteUser(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "The user cannot be deleted.");
        }
    }

}
