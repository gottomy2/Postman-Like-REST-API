package edu.pjatk.postman.controller.user.model;

import lombok.*;
import java.util.List;

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
