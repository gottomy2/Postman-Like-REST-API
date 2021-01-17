package edu.pjatk.postman.repository;

import edu.pjatk.postman.repository.model.Response;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

/**
 * @author Igor Motowidło (gottomy2)
 * Simple JpaRepository for Response class
 */
@Repository
public interface ResponseRepository extends JpaRepository<Response,Long> {

    /**
     * @param id request_id to search by
     * @return Optional<Response> List of responses with request_id=id
     */
    @Query("select r from Response r where r.requestId=?1")
    Optional<Response> findResponseByRequestId(Long id);

    /**
     * @param id id parameter of Response object
     * @return Optional<Response> with Response.id=id
     */
    @Query("select r from Response r where r.id=?1")
    Optional<Response> getResponseById(Long id);

    /**
     * @return List<Long> of all ids in responses table
     */
    @Query("select r.id from Response r")
    List<Long> getAllIds();
}
