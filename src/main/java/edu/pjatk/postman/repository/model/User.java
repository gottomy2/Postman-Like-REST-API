package edu.pjatk.postman.repository.model;

import lombok.*;
import javax.persistence.*;

/**
 * @author Igor Motowidło (gottomy2)
 * Creates the 'users' table on the database
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username,password,email,privilege;

    public User(String username, String password, String email,String privilege) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.privilege=privilege;
    }
}
