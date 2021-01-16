package edu.pjatk.postman.db.controller.response.model;

import edu.pjatk.postman.db.repository.model.User;
import lombok.*;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class GetRequestResponse {
    private Long id;
    private User user;
    private String url;
}
