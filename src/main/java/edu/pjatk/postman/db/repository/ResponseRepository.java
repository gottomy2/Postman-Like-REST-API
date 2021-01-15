package edu.pjatk.postman.db.repository;

import edu.pjatk.postman.db.repository.model.Response;
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
     * Finds all responses by request_id
     * @param id request_id to search by
     * @return Optional<Response> List of responses with request_id=id
     */
    @Query("select r from Response r where r.request=?1")
    Optional<Response> findAllResponsesByRequestId(Long id);

    /**
     * Finds Response by id
     * @param id id parameter of Response object
     * @return Response Object
     */
    @Query("select r from Response r where r.id=?1")
    Response getResponseById(Long id);
}
