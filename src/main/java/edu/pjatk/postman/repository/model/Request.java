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

    private String url,header,type;

    public Request(Long userId, String url, String header, String type) {
        this.userId = userId;
        this.url = url;
        this.header=header;
        this.type=type;
    }
}
