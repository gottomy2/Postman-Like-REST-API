package edu.pjatk.postman.repository;

import edu.pjatk.postman.repository.model.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Igor Motowidło (gottomy2)
 * Simple JpaRepository for Param class
 */
@Repository
public interface ParamRepository extends JpaRepository<Param,Long> {

    /**
     * Finds particular Param Object by id
     * @param id id of the Param we're looking for
     * @return Param Object
     */
    @Query("select p from Param p where p.id=?1")
    Optional<Param> getParamById(Long id);

    /**
     * @return List of all Long Ids within params table
     */
    @Query("select p.id from Param p")
    List<Long> getAllIds();

    /**
     * Finds all Params with request_id=id
     * @param id Id of the request to which Param belongs
     * @return Optional list of the Params with specified request_id
     */
    @Query("select p from Param p where p.requestId=?1")
    List<Param> findParamsByRequestId(Long id);
}
