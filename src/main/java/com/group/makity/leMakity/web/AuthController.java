package com.group.makity.leMakity.web;

import com.group.makity.leMakity.dtos.AppUserDTO;
import com.group.makity.leMakity.dtos.AuthResponseDTO;
import com.group.makity.leMakity.dtos.LoginDTO;
import com.group.makity.leMakity.dtos.Response;
import com.group.makity.leMakity.exceptions.AppUserNotFoundException;
import com.group.makity.leMakity.security.JWTGenerator;
import com.group.makity.leMakity.security.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/auth")
@CrossOrigin(origins = "*")
@Slf4j
public class AuthController {

    private final AccountService accountService;
    private final AuthenticationManager authenticationManager;
    private final JWTGenerator jwtGenerator;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AuthController(AccountService accountService, AuthenticationManager authenticationManager, JWTGenerator jwtGenerator, PasswordEncoder passwordEncoder) {
        this.accountService = accountService;
        this.authenticationManager = authenticationManager;
        this.jwtGenerator = jwtGenerator;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Response login(@RequestBody LoginDTO loginDto){
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginDto.getEmail(),
                            loginDto.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwtGenerator.generateToken(authentication);
            log.info(token);
            return Response.builder()
                    .reason("")
                    .statusCode(200)
                    .message("success")
                    .status(HttpStatus.OK)
                    .data(Map.of("response", new AuthResponseDTO(token)))
                    .build();
        }catch (RuntimeException e){
            log.error(e.getMessage());
            return Response.builder()
                    .reason(e.getMessage())
                    .statusCode(403)
                    .message("failed")
                    .status(HttpStatus.FORBIDDEN)
                    .data(Map.of("respose", "null"))
                    .build();
        } catch (AppUserNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    @PostMapping(value = "/register")
    public ResponseEntity<AppUserDTO> register(@Validated @RequestBody AppUserDTO appUserDTO) {
        try {
            AppUserDTO savedUser = accountService.saveUser(appUserDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/current-user")
    public ResponseEntity<AppUserDTO> getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        try {
            AppUserDTO userDto = accountService.findByEmail(auth.getName());
            return ResponseEntity.ok(userDto);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NETWORK_AUTHENTICATION_REQUIRED).build();
        }

    }

    @GetMapping("/users")
    public List<AppUserDTO> listUsers(){
        return accountService.listUsers();
    }

}
