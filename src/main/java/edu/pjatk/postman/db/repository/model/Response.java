package edu.pjatk.postman.db.repository.model;

import lombok.*;

import javax.persistence.*;

/**
 * @author gottomy2
 * Creates the 'responses' table on the database
 * ID - auto generated id of response.
 * request_id - id of request table entity for which was the response provided.
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

    @ManyToOne
    @JoinColumn(name= "request_id")
    private Request request;
}
