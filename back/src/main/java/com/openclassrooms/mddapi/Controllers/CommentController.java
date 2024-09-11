package com.openclassrooms.mddapi.Controllers;

import com.openclassrooms.mddapi.Dtos.CommentDTO.CommentDto;
import com.openclassrooms.mddapi.Dtos.CommentDTO.CommentDtoResponse;
import com.openclassrooms.mddapi.Services.Interfaces.CommentService;
import com.openclassrooms.mddapi.exeptions.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.stream.Collectors;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("api")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Operation(summary = "Créer un nouveau Commentaire")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Commentaire créé avec succès"),
            @ApiResponse(responseCode = "400", description = "Erreur de création"),
            @ApiResponse(responseCode = "500", description = "Serveur Indisponible")
    })
    /**
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
            throw new ValidationException("Validation errors: " + errors);
        }
        return commentService.createComment(commentDto, principal);
    }
}