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
@Table(name = "bodies")
public class Body {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long requestId;

    String name,value;

    public Body(Long requestId, String name, String value) {
        this.requestId = requestId;
        this.name = name;
        this.value = value;
    }
}
