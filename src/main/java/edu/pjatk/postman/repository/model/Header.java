package edu.pjatk.postman.repository.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "headers")
public class Header {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long requestId;

    private String name,value;

    public Header(Long requestId, String name, String value) {
        this.requestId = requestId;
        this.name = name;
        this.value = value;
    }
}
