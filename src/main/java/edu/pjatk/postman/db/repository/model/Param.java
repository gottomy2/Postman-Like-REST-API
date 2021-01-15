package edu.pjatk.postman.db.repository.model;

import lombok.*;
import javax.persistence.*;

/**
 * @author gottomy2
 * Creates the 'params' table on the database
 * ID - auto generated id of param.
 * request_id - id of request table entity for which was the param provided.
 * name - name of the param | example: "username"
 * value - value of the param | example : "admin"
 */

@Getter
@Setter
@Builder
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "params")
public class Param {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @ManyToOne
    @JoinColumn(name = "request_id")
    private Request request;

    String name,value;
}
