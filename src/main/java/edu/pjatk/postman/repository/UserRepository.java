package edu.pjatk.postman.repository;

import edu.pjatk.postman.repository.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;

/**
 * @author Igor Motowidło (gottomy2)
 * Simple JpaRepository for User class
 */

public interface UserRepository extends JpaRepository<User,Long> {

    /**
     * @return List - List of all Long UserIds
     */
    @Query("select u.id from User u")
    List<Long> findAllIds();


    /**
     * @param id ID of the user in the database
     * @return User user - with the specified id
     */
    @Query("select u from User u where u.id=?1")
    Optional<User> findUserById(Long id);
}
