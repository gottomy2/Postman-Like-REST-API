package edu.pjatk.postman.controller.user.model;

import lombok.*;

/**
 * @author Igor Motowidło (gottomy2)
 * Simple Get Response model for UserController
 */

@Getter
@Setter
@Builder
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class GetUserResponse {
    private Long id;

    private String username,password,email,privilege;
}
