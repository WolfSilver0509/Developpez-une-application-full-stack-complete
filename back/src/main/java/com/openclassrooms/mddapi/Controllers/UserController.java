package com.openclassrooms.mddapi.Controllers;


import com.openclassrooms.mddapi.Dtos.UserDto.UserDto;
import com.openclassrooms.mddapi.Models.User;
import com.openclassrooms.mddapi.Services.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api")
@RestController
public class UserController
{
    private UserService userService;

    /**
     * Endpoint pour récupérer les informations d'un utilisateur connectée
     * @param user le user actuellement connecter
     * @return les informations du user actuellement connecter
     *
     */
    @GetMapping("/me")
    public UserDto getCurrentUser(@AuthenticationPrincipal User user) {
        return userService.getCurrentUser(user);
    }

}
