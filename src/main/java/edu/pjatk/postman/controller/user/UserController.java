package edu.pjatk.postman.controller.user;

import edu.pjatk.postman.controller.user.model.GetUserResponse;
import edu.pjatk.postman.controller.user.model.GetUsersResponses;
import edu.pjatk.postman.controller.user.model.PostUserRequest;
import edu.pjatk.postman.controller.user.model.PutUserRequest;
import edu.pjatk.postman.repository.model.User;
import edu.pjatk.postman.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

/**
 * @author Igor Motowidło (gottomy2)
 * Complete API for user entities
 */

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;


    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Finds user in the database by id
     * @param id id of the user to find in database
     * @return if user exists returns STATUS CODE: 200 with user entity within body | if user does not exist returns STATUS CODE: 404
     */
    @GetMapping("/getUserById/{userId}")
    public ResponseEntity<GetUserResponse> getUser(@PathVariable("userId") Long id) {
        Optional<User> user = userService.findUserById(id);
        return user.map(value -> ResponseEntity.ok(new GetUserResponse(value.getId(),value.getEmail(),value.getPassword(),value.getUsername(),value.getPrivilege())))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Finds all ids of users in the database
     * @return all Ids of users from the database
     */
    @GetMapping("/getAllIds")
    public GetUsersResponses getAllUsersIds(){
        return new GetUsersResponses(userService.findAllUsers());
    }

    /**
     * Creates new User object on the database (adds new user to the database)
     * @param request PostUserRequest Object -
     * @return ResponseEntity object found via getUserById API mapping
     */
    @PostMapping("/createUser")
    public ResponseEntity<Void> postUser(@RequestBody PostUserRequest request){
        User user = new User(request.getUsername(),request.getPassword(),request.getEmail(),request.getPrivilege());
        userService.createUser(user);
        return ResponseEntity.created(URI.create("http://localhost:9090/user/getUserById/"+user.getId())).build();
    }

    /**
     * Updates existing user on the database
     * @param request PutUserRequest object containing basic user parameters
     * @return STATUS CODE: 200 on success | STATUS CODE: 404 on failure
     */
    @PutMapping("/updateUser")
    public ResponseEntity<Void> putUser(@RequestBody PutUserRequest request){
        Optional<User> user = userService.findUserById(request.getId());
        if(user.isPresent()){
            user.get().setId(request.getId());
            user.get().setUsername(request.getUsername());
            user.get().setEmail(request.getEmail());
            user.get().setPassword(request.getPassword());
            userService.updateUser(user.get());
            return ResponseEntity.ok().build();
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Removes specific user from the database
     * @param id of the user to remove
     * @return STATUS CODE: 200 on success | STATUS CODE: 404 on failure
     */
    @DeleteMapping("/deleteUser/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable("userId") Long id){
        Optional<User> user = userService.findUserById(id);
        if(user.isPresent()){
            userService.deleteUser(user.get());
            return ResponseEntity.ok().build();
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
}
