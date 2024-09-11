package com.openclassrooms.mddapi.Controllers;

import com.openclassrooms.mddapi.Models.User;
import com.openclassrooms.mddapi.Dtos.LoginUserDto;
import com.openclassrooms.mddapi.Dtos.RegisterUserDto;
import com.openclassrooms.mddapi.Responses.LoginResponse;
import com.openclassrooms.mddapi.Services.Interfaces.AuthenticationService;
import com.openclassrooms.mddapi.Services.Interfaces.JwtService;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/auth")
@RestController
public class AuthenticationController {
    private final JwtService jwtService;
    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @Operation(summary = "Enregistrer un nouvel utilisateur")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Utilisateur enregistré avec succès"),
            @ApiResponse(responseCode = "400", description = "Erreur d'enregistrement")
    })
    /**
     * Enregistrer un nouvel utilisateur.
     * @param registerUserDto les informations de l'utilisateur à enregistrer
     * @return l'utilisateur enregistré
     */
    @PostMapping("/register")
    public User register(@Valid @RequestBody RegisterUserDto registerUserDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errors = bindingResult.getFieldErrors().stream()
                    .map(FieldError::getDefaultMessage)
                    .collect(Collectors.joining(", "));
            throw new RuntimeException("Validation errors: " + errors);
        }
        return authenticationService.signup(registerUserDto);
    }

    @Operation(summary = "Authentifier un utilisateur")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Utilisateur authentifié avec succès"),
            @ApiResponse(responseCode = "400", description = "Erreur d'authentification")
    })
    /**
     * Authentifier un utilisateur.
     * @param loginUserDto les informations de l'utilisateur à authentifier
     * @return la réponse de l'authentification
     */
    @PostMapping("/login")
    public LoginResponse authenticate(@Valid @RequestBody LoginUserDto loginUserDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errors = bindingResult.getFieldErrors().stream()
                    .map(FieldError::getDefaultMessage)
                    .collect(Collectors.joining(", "));
            throw new RuntimeException("Validation errors: " + errors);
        }
        User authenticatedUser = authenticationService.authenticate(loginUserDto);
        String jwtToken = jwtService.generateToken(authenticatedUser);
        return new LoginResponse().setToken(jwtToken).setExpiresIn(jwtService.getExpirationTime());
    }
}