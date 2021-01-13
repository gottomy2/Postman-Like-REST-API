package edu.pjatk.postman.db.repository;

import edu.pjatk.postman.db.repository.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    @Query("select u.id from User u")
    List<Long> findId();

    @Query("select u.id from User u where u.name = ?1")
    Long findIdByName(String name);

    @Query("select u from User u where u.id=?1")
    Optional<User> findUserById(Long id);
}
