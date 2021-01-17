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
public class GetResponseResponse {
    Long id;
    private Long requestId;
    private String response;
}
