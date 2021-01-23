package edu.pjatk.postman.controller.user.model;

import lombok.*;

/**
 * @author Igor Motowidło (gottomy2)
 * Simple Post Request model for UserController
 */

@Getter
@Setter
@Builder
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PostUserRequest {
    private String username,password,email,privilege;
}
