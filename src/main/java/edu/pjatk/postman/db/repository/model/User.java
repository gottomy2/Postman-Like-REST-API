package edu.pjatk.postman.db.repository.model;

import lombok.*;
import javax.persistence.*;

/**
 * @author Igor Motowidło (gottomy2)
 * Simple User Entity Class
 * id - auto generated value for the database,
 * username - User username,
 * password - User password,
 * email - User email.
 */

@Getter
@Setter
@Builder
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue()
    private Long id;

    private String username,password,email;
}
