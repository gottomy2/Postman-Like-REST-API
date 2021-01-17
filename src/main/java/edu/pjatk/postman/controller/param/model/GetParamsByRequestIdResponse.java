package edu.pjatk.postman.controller.param.model;

import edu.pjatk.postman.repository.model.Param;
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
