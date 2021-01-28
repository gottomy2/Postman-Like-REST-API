package edu.pjatk.postman.repository.model;

import lombok.*;
import javax.persistence.*;

/**
 * @author Igor Motowidlo (gottomy2)
 * Creates the 'requests' table on the database
 */

@Getter
@Setter
@Builder
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "requests")
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private String host;
    private String url;
    private String type;

    public Request(Long userId, String host,String url,String type) {
        this.userId = userId;
        this.host=host;
        this.url = url;
        this.type=type;
    }
}
