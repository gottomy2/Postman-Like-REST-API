package edu.pjatk.postman.repository;

import edu.pjatk.postman.repository.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


/**
 * @author Igor Motowidło (gottomy2)
 * Simple JpaRepository for Request class
 */
@Repository
public interface RequestRepository extends JpaRepository<Request,Long> {

    /**
     * @return List of all Long ids from requests table
     */
    @Query("select r.id from Request r")
    List<Long> findAllIds();

    /**
     * @param id - id of user to search the requests table for
     * @return Optional all Requests where Request.userId=id
     */
    @Query("select r from Request r where r.userId=?1")
    Optional<Request> requestList(Long id);
}
