package edu.pjatk.postman.repository.model;

import lombok.*;
import javax.persistence.*;

/**
 * @author Igor Motowidlo (gottomy2)
 * Creates the 'scenarios' table on the database
 */

@Getter
@Setter
@Builder
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "scenarios")
public class Scenario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private String requestIds,name,description;

    public Scenario(Long userId, String requestIds, String name, String description) {
        this.userId = userId;
        this.requestIds = requestIds;
        this.name = name;
        this.description = description;
    }
}
