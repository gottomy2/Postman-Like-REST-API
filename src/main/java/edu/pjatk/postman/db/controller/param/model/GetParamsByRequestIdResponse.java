package edu.pjatk.postman.db.controller.param.model;

import edu.pjatk.postman.db.repository.model.Param;
import lombok.*;
import java.util.Optional;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class GetParamsByRequestIdResponse {
    Optional<Param> paramList;
}
