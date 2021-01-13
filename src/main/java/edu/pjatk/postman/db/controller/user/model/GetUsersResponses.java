package edu.pjatk.postman.db.controller.user.model;

import lombok.*;

import java.util.HashMap;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class GetUsersResponses {
    private HashMap<Long,String> map=new HashMap<Long,String>();
}
