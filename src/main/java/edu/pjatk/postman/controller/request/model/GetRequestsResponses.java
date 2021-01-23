package edu.pjatk.postman.controller.request.model;

import lombok.*;

import java.util.List;


/**
 * @author Igor Motowidło (gottomy2)
 * Simple Get Response model for RequestController
 */

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
