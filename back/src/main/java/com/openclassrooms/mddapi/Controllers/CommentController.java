package com.openclassrooms.mddapi.Controllers;

import com.openclassrooms.mddapi.Dtos.CommentDTO.CommentDto;
import com.openclassrooms.mddapi.Dtos.CommentDTO.CommentDtoResponse;
import com.openclassrooms.mddapi.Services.Interfaces.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api")
public class CommentController {

    @Autowired
    private CommentService commentService;

    /*
     * Point de terminaison pour créer un nouveau Comment.
     * Prend en entrée un DTO de création du Comment et les informations de l'utilisateur.
     * Retourne une réponse contenant le DTO de la réponse de création de Comment.
     */
    @PostMapping("/comments")
    public CommentDtoResponse createPost(@Valid @ModelAttribute CommentDto commentDto, BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors()) {
            String errors = bindingResult.getFieldErrors().stream()
                    .map(FieldError::getDefaultMessage)
                    .collect(Collectors.joining(", "));
            throw new RuntimeException("Validation errors: " + errors);
        }
        return commentService.createComment(commentDto, principal);
    }
}