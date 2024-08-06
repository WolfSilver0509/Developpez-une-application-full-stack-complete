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

@RequestMapping("api")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Endpoint pour récupérer les informations de l'utilisateur actuellement connecté.
     * @param user l'utilisateur actuellement connecté
     * @return les informations de l'utilisateur actuellement connecté
     */
    @GetMapping("/me")
    public UserDto getCurrentUser(@AuthenticationPrincipal User user) {
        return userService.getCurrentUser(user);
    }

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

    /**
     * Endpoint pour mettre à jour les informations de l'utilisateur actuellement connecté.
     * @param user l'utilisateur actuellement connecté
     * @param updateDto les informations de mise à jour de l'utilisateur
     * @param bindingResult les erreurs de validation
     * @return les informations de l'utilisateur actuellement connecté mises à jour
     */
    @PutMapping("/me")
    public UserDto updateUser(@AuthenticationPrincipal User user, @Valid @ModelAttribute UserUpdateDto updateDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errors = bindingResult.getFieldErrors().stream()
                    .map(FieldError::getDefaultMessage)
                    .collect(Collectors.joining(", "));
            throw new ValidationException("Validation errors: " + errors);
        }
        try {
            return userService.updateUser(user.getId(), updateDto);
        } catch (NoSuchElementException e) {
            throw new NotFoundException("User not found");
        }
    }
}