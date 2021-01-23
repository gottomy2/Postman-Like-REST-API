package edu.pjatk.postman.controller.user.model;

import lombok.*;
import java.util.List;

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
public class GetUsersResponses {
    private List<Long> idList;
}
