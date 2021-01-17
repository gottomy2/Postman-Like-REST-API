package edu.pjatk.postman.controller.user.model;

import lombok.*;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PostUserRequest {
    private Long id;

    private String username,password,email;
}
