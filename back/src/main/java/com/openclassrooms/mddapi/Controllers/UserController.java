package com.openclassrooms.mddapi.Controllers;


import com.openclassrooms.mddapi.Dtos.TopicDTO.SubscriptionResponseDto;
import com.openclassrooms.mddapi.Dtos.UserDto.UserDto;
import com.openclassrooms.mddapi.Dtos.UserDto.UserUpdateDto;
import com.openclassrooms.mddapi.Models.User;
import com.openclassrooms.mddapi.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.Optional;

@RequestMapping("api")
@RestController
public class UserController
{

    @Autowired
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


    @GetMapping("/user/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Integer id) {
        try {
            UserDto userDto = userService.getUserById(id);
            return ResponseEntity.ok(userDto);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/me")
    public ResponseEntity<UserDto> updateUser(@AuthenticationPrincipal User user, @ModelAttribute UserUpdateDto updateDto) {
        try {
            UserDto updatedUser = userService.updateUser(user.getId(), updateDto);
            return ResponseEntity.ok(updatedUser);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{userId}/topics/{topicId}")
    public ResponseEntity<SubscriptionResponseDto> subscribeToTopic(@PathVariable Integer userId, @PathVariable Integer topicId) {
        try {
            SubscriptionResponseDto responseDto = userService.subscribeToTopic(userId, topicId);
            return ResponseEntity.ok(responseDto);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{userId}/topics/{topicId}")
    public ResponseEntity<UserDto> unsubscribeFromTopic(@PathVariable Integer userId, @PathVariable Integer topicId) {
        try {
            UserDto updatedUser = userService.unsubscribeFromTopic(userId, topicId);
            return ResponseEntity.ok(updatedUser);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
