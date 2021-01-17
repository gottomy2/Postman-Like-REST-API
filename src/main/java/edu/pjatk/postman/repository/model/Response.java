package edu.pjatk.postman.repository.model;

import lombok.*;
import javax.persistence.*;

/**
 * @author Igor Motowidło (gottomy2)
 * Creates the 'responses' table on the database
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
