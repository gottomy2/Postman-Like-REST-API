package edu.pjatk.postman.db.controller.param.model;

import edu.pjatk.postman.db.repository.model.Param;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class GetParamsResponse {
    private Param[] paramList;
}
