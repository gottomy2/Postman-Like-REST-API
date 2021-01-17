package edu.pjatk.postman.controller.user.model;

import lombok.*;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class GetUserResponse {
    private Long id;

    private String username,password,email;
}
