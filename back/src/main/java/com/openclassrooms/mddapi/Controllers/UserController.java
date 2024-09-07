package com.openclassrooms.mddapi.Controllers;

import com.openclassrooms.mddapi.Dtos.UserDto.UserDto;
import com.openclassrooms.mddapi.Dtos.UserDto.UserUpdateDto;
import com.openclassrooms.mddapi.Models.User;
import com.openclassrooms.mddapi.Services.Interfaces.UserService;
import com.openclassrooms.mddapi.exeptions.NotFoundException;
import com.openclassrooms.mddapi.exeptions.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api")
@RestController
public class UserController {

    @Autowired
    private UserService userService;


    @Operation(summary = "Récupérer les informations de l'utilisateur actuellement connecté")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Utilisateur actuellement connecté récupéré avec succès"),
            @ApiResponse(responseCode = "404", description = "Utilisateur non trouvé")
    })
    /**
     * Endpoint pour récupérer les informations de l'utilisateur actuellement connecté.
     * @param user l'utilisateur actuellement connecté
     * @return les informations de l'utilisateur actuellement connecté
     */
    @GetMapping("/me")
    public UserDto getCurrentUser(@AuthenticationPrincipal User user) {
        return userService.getCurrentUser(user);
    }

    @Operation(summary = "Récupérer les informations d'un utilisateur par son ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Utilisateur récupéré avec succès"),
            @ApiResponse(responseCode = "404", description = "Utilisateur non trouvé")
    })
    /**
     * Endpoint pour récupérer les informations d'un utilisateur par son ID.
     * @param id l'ID de l'utilisateur à récupérer
     * @return les informations de l'utilisateur correspondant à l'ID
     */
    @GetMapping("/user/{id}")
    public UserDto getUserById(@PathVariable Integer id) {
        try {
            return userService.getUserById(id);
        } catch (NoSuchElementException e) {
            throw new NotFoundException("User not found");
        }
    }

    @Operation(summary = "Mettre à jour les informations de l'utilisateur actuellement connecté")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Utilisateur actuellement connecté mis à jour avec succès"),
            @ApiResponse(responseCode = "404", description = "Utilisateur non trouvé")
    })
    /**
     * Endpoint pour mettre à jour les informations de l'utilisateur actuellement connecté.
     * @param updateDto les informations de mise à jour de l'utilisateur
     * @param bindingResult les erreurs de validation
     * @return les informations de l'utilisateur actuellement connecté mises à jour
     */
    @PutMapping("/me")
    public UserDto updateUser(@Valid @ModelAttribute UserUpdateDto updateDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errors = bindingResult.getFieldErrors().stream()
                    .map(FieldError::getDefaultMessage)
                    .collect(Collectors.joining(", "));
            throw new ValidationException("Validation errors: " + errors);
        }
        try {
            return userService.updateUser(updateDto);
        } catch (NoSuchElementException e) {
            throw new NotFoundException("User not found");
        }
    }
}