package edu.pjatk.postman.db.controller.user.model;

import lombok.*;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PutUserRequest {
    private Long id;

    private String username,password,email;
}
