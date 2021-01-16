package edu.pjatk.postman.db.service;

import edu.pjatk.postman.db.repository.UserRepository;
import edu.pjatk.postman.db.repository.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * @author Igor Motowidło (gottomy2)
 * Simple Service for UserRepository class
 */

@Service
public class UserService {
    private UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    /**
     * Finds all users from the database
     * @return List<User>
     */
    public List<Long> findAllUsers(){
        return repository.findAllIds();
    }

    /**
     * Finds user with specified id in the database
     * @param id Id of the user we're looking for
     * @return User Object
     */
    public Optional<User> findUserById(Long id){
        return repository.findUserById(id);
    }

    /**
     * Creates new User in the database.
     * @param user User Object
     */
    public void createUser(User user){
        repository.save(user);
    }

    /**
     * Updates existing User, or creating new User in the database.
     * @param user User Object
     */
    public void updateUser(User user){
        repository.save(user);
    }

    /**
     * Removes User entity from the database.
     * @param user User Object
     */
    public void deleteUser(User user){
        repository.delete(user);
    }
}