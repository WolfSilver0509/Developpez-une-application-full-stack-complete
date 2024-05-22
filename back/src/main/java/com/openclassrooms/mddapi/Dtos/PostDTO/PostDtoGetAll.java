package com.openclassrooms.mddapi.Dtos.PostDTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class PostDtoGetAll {
    private List<PostDto> posts;
}
