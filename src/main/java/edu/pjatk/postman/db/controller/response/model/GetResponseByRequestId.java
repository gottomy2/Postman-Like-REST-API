package edu.pjatk.postman.db.controller.response.model;

import edu.pjatk.postman.db.repository.model.Response;
import lombok.*;
import java.util.Optional;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class GetResponseByRequestId {
    private Optional<Response> responseList;
}
