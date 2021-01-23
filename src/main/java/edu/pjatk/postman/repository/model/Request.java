package edu.pjatk.postman.repository.model;

import lombok.*;

import javax.persistence.*;

/**
 * @author Igor Motowidlo (gottomy2)
 * Simple Entity class for Requests
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

    private String url;

    public Request(Long userId, String url) {
        this.userId = userId;
        this.url = url;
    }
}
