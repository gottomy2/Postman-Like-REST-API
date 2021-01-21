package edu.pjatk.postman.repository.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * @author gottomy2
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long userId;

    private String requestIds,name,description;
}
