package com.openclassrooms.mddapi.Dtos.UserDto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter
@AllArgsConstructor @NoArgsConstructor
public class UserUpdateDto {
    private String name;
    private String email;
    private String password;

}
