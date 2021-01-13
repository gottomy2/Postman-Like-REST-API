package edu.pjatk.postman.db.controller.user;

import edu.pjatk.postman.db.controller.user.model.GetUserResponse;
import edu.pjatk.postman.db.repository.model.User;
import edu.pjatk.postman.db.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

//    @GetMapping("{userId}")
//    public ResponseEntity<GetUserResponse> getBranch(@PathVariable("userId") Long userId) {
//        Optional<User> branch = userService.findUser(userId);
//        return branch.map(value -> ResponseEntity.ok(new GetUserResponse(value.getId(), value.getName())))
//                .orElseGet(() -> ResponseEntity.notFound().build());
//    }

}
