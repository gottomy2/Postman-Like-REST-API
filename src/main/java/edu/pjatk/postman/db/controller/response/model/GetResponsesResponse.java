package edu.pjatk.postman.db.controller.response.model;

import lombok.*;
import java.util.List;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class GetResponsesResponse {
    private List<Long> responseIds;
}
