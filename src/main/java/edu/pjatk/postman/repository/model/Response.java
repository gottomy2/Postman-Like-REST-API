package edu.pjatk.postman.repository.model;

import lombok.*;

import javax.persistence.*;

/**
 * @author gottomy2
 * Creates the 'responses' table on the database
 * ID - auto generated id of response.
 * requestId - id of request table entity for which was the response provided.
 * response - response which has been achived through sending the request with Request.id = Response.requestId
*/

@Getter
@Setter
@Builder
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "responses")
public class Response {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    private Long requestId;

    private String response;
}
