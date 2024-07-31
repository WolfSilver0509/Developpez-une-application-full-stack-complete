package com.openclassrooms.mddapi.Services;


import com.openclassrooms.mddapi.Dtos.TopicDTO.SubscriptionResponseDto;
import com.openclassrooms.mddapi.Dtos.UserDto.DtoConvert;
import com.openclassrooms.mddapi.Dtos.UserDto.UserDto;
import com.openclassrooms.mddapi.Dtos.UserDto.UserUpdateDto;
import com.openclassrooms.mddapi.Models.User;
import com.openclassrooms.mddapi.Models.Topic;
import com.openclassrooms.mddapi.Repositorys.UserRepository;
import com.openclassrooms.mddapi.Repositorys.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Service pour la gestion des utilisateurs.
 */
@Service
public class UserService {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private DtoConvert dtoConverter;

    @Autowired
    private JwtService jwtService;

    public Optional<User> findById(Integer id) {
        return userRepository.findById(id);
    }

    /**
     * Récupère l'utilisateur courant.
     * @return L'utilisateur courant.
     */
    public UserDto getCurrentUser(User user) {
        return dtoConverter.convertToUserDto(user);
    }

    public UserDto getUserById(Integer id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return getCurrentUser(user);
        } else {
            throw new NoSuchElementException("User not found with id: " + id);
        }

    }

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


    public SubscriptionResponseDto subscribeToTopic(Integer userId, Integer topicId) {
        Optional<User> userOptional = userRepository.findById(userId);
        Optional<Topic> topicOptional = topicRepository.findById(topicId);
        if (userOptional.isPresent() && topicOptional.isPresent()) {
            User user = userOptional.get();
            Topic topic = topicOptional.get();
            topic.getSubscribers().add(user);
            topicRepository.save(topic);
            SubscriptionResponseDto responseDto = new SubscriptionResponseDto();
            responseDto.setUserId(userId);
            responseDto.setTopicId(topicId);
            responseDto.setMessage("User is successfully subscribed to the topic");
            return responseDto;
        } else {
            throw new NoSuchElementException("User or Topic not found");
        }
    }

        public UserDto unsubscribeFromTopic(Integer userId, Integer topicId) {
            Optional<User> userOptional = userRepository.findById(userId);
            Optional<Topic> topicOptional = topicRepository.findById(topicId);
            if (userOptional.isPresent() && topicOptional.isPresent()) {
                User user = userOptional.get();
                Topic topic = topicOptional.get();
                user.getSubscribedTopics().remove(topic);
                userRepository.save(user);
                return getCurrentUser(user);
            } else {
                throw new NoSuchElementException("User or Topic not found");
            }
        }








}