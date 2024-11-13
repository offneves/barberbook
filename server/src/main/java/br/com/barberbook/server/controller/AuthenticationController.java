package br.com.barberbook.server.controller;

import br.com.barberbook.server.auth.security.TokenService;
import br.com.barberbook.server.dto.request.AuthenticationRequestDTO;
import br.com.barberbook.server.dto.request.LoginResponseDTO;
import br.com.barberbook.server.dto.request.UserRequestDTO;
import br.com.barberbook.server.model.UserModel;
import br.com.barberbook.server.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationRequestDTO authenticationRequestDTO) {
        try {
            var usernamePassword = new UsernamePasswordAuthenticationToken(
                    authenticationRequestDTO.login(), authenticationRequestDTO.password()
            );
            var authentication = this.authenticationManager.authenticate(usernamePassword);
            var token = tokenService.generateToken((UserModel) authentication.getPrincipal());
            return ResponseEntity.ok(new LoginResponseDTO(token));
        } catch (AuthenticationCredentialsNotFoundException e) {
            throw new AuthenticationCredentialsNotFoundException("The credentials provided are incorrect.", e);
        }
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid UserRequestDTO userRequestDTO) {
        try {
            if (this.userRepository.findByEmail(userRequestDTO.email()).isPresent())
                return ResponseEntity.badRequest().build();
            String encryptedPassword = new BCryptPasswordEncoder().encode(userRequestDTO.password());
            UserModel user = new UserModel();
            BeanUtils.copyProperties(userRequestDTO, user);
            user.setPassword(encryptedPassword);
            userRepository.save(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    new LoginResponseDTO(tokenService.generateToken(user))
            );
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error. Invalid or used data.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: Internal server error.");
        }
    }

}
