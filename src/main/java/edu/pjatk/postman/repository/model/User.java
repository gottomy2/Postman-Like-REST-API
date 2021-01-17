package edu.pjatk.postman.repository.model;

import lombok.*;
import javax.persistence.*;

/**
 * @author Igor Motowidło (gottomy2)
 * Simple User Entity Class
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
