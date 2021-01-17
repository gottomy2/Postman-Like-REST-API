package edu.pjatk.postman.db.controller.param.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class GetParamsResponses {
    private List<Long> paramIdList;
}
