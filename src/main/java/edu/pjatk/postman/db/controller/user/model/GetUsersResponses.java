package edu.pjatk.postman.db.controller.user.model;

import edu.pjatk.postman.db.repository.model.User;
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
