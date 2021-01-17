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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Access(AccessType.PROPERTY)
    private Long id;

    public Request(Long id){
        this.id = id;
    }

    private Long userId;

    private String url;
}
