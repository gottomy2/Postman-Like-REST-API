package edu.pjatk.postman.repository;

import edu.pjatk.postman.repository.model.Scenario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

/**
 * @author Igor Motowidło (gottomy2)
 * Simple JpaRepository for Scenario class
 */
@Repository
public interface ScenarioRepository extends JpaRepository<Scenario,Long> {

    /**
     * @return List of all Long ids from scenarios table
     */
    @Query("select s.id from Scenario s")
    List<Long> findAllIds();


    /**
     * @param id id of the user to search the scenarios table for
     * @return Optional of all scenarios where userId=id
     */
    @Query("select s from Scenario s where s.userId=?1")
    Optional<Scenario> findScenariosByUserId(Long id);
}
