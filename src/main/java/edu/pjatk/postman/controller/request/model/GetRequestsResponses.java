package edu.pjatk.postman.controller.request.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class GetRequestsResponses {
    private List<Long> requestList;
}
