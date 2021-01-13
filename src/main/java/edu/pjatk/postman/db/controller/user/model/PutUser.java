package edu.pjatk.postman.db.controller.user.model;

import lombok.*;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PutUser {
    private Long id;
    private String name;
}
