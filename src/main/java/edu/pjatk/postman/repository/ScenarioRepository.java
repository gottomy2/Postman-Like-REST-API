package edu.pjatk.postman.repository;

import edu.pjatk.postman.repository.model.Scenario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ScenarioRepository extends JpaRepository<Scenario,Long> {

    @Query("select s.id from Scenario s")
    List<Long> findAllIds();

    @Query("select s from Scenario s where s.id=?1")
    Optional<Scenario> findScenarioById(Long id);

    @Query("select s from Scenario s where s.userId=?1")
    Optional<Scenario> findScenariosByUserId(Long id);
}
