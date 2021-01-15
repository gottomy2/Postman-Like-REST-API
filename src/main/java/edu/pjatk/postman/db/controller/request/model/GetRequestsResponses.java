package edu.pjatk.postman.db.controller.request.model;

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
public class GetRequestsResponses {
    private List<Long> idList = new ArrayList<>();

}
