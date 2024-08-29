package com.openclassrooms.mddapi.Services;

import com.openclassrooms.mddapi.Dtos.TopicDTO.TopicDto;
import com.openclassrooms.mddapi.Dtos.UserDto.UserDto;
import com.openclassrooms.mddapi.Dtos.UserDto.UserUpdateDto;
import com.openclassrooms.mddapi.Models.User;
import com.openclassrooms.mddapi.Repositorys.TopicRepository;
import com.openclassrooms.mddapi.Repositorys.UserRepository;
import com.openclassrooms.mddapi.Services.Interfaces.AuthenticationService;
import com.openclassrooms.mddapi.Services.Interfaces.JwtService;
import com.openclassrooms.mddapi.Services.Interfaces.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final TopicRepository topicRepository;

    public UserServiceImpl(AuthenticationService authenticationService, UserRepository userRepository, TopicRepository topicRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.topicRepository = topicRepository;
    }


    @Override
    public Optional<User> findById(Integer id) {
        return userRepository.findById(id);    }

    @Override
    public UserDto getCurrentUser(User user) {
        List<TopicDto> topics = topicRepository.findByUsers_Id(user.getId())
                .stream()
                .map(topic -> new TopicDto(topic.getId(), topic.getTitle(), topic.getDescription(), topic.getCreated_at(), topic.getUpdated_at()))
                .collect(Collectors.toList());

        return new UserDto(user.getId(), user.getName(), user.getEmail(), user.getCreatedAt(), user.getUpdatedAt(), topics);
    }

    @Override
    public UserDto getUserById(Integer id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return getCurrentUser(user);
        } else {
            throw new NoSuchElementException("User not found with id: " + id);
        }
    }

    @Override
    public UserDto updateUser(Integer id, UserUpdateDto updateDto) {
        // Récupérer l'utilisateur actuellement authentifié
        User authenticatedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // Vérifier si l'utilisateur actuellement authentifié est autorisé à modifier l'utilisateur spécifié par l'ID
        if (authenticatedUser.getId().equals(id)) {
            Optional<User> userOptional = userRepository.findById(id);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                if (updateDto.getName() != null) {
                    user.setName(updateDto.getName());
                }
                if (updateDto.getEmail() != null) {
                    user.setEmail(updateDto.getEmail());
                }
                // Vérifier si le mot de passe est fourni avant de le mettre à jour
                if (updateDto.getPassword() != null) {
                    user.setPassword(passwordEncoder.encode(updateDto.getPassword()));
                }
                userRepository.save(user);

                // Générer un nouveau jeton JWT
                String newJwtToken = jwtService.generateToken(user);

                // Mettre à jour le jeton JWT dans la réponse
                UserDto userDto = getCurrentUser(user);
                userDto.setJwtToken(newJwtToken);

                return userDto;
            } else {
                throw new NoSuchElementException("User not found with id: " + id);
            }
        } else {
            throw new NoSuchElementException("You are not authorized to update this user");
        }
    }

}
