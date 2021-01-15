package edu.pjatk.postman.db.repository;

import edu.pjatk.postman.db.repository.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

/**
 * @author Igor Motowidło (gottomy2)
 * Simple JpaRepository for User class
 */

public interface UserRepository extends JpaRepository<User,Long> {

    /**
     * @return List<Long> - List of all UserIds
     */
    @Query("select u.id from User u")
    List<Long> findId();


    /**
     * @param username name of the User object we are looking for.
     * @return Long id - id of the User we've been searching for.
     */
    @Query("select u.id from User u where u.username = ?1")
    Long findIdByName(String username);


    /**
     * @param id ID of the user in the database
     * @return User user - with the specified id
     */
    @Query("select u from User u where u.id=?1")
    User findUserById(Long id);
}
