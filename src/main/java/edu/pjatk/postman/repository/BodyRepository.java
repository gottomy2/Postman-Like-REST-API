package edu.pjatk.postman.repository;

import edu.pjatk.postman.repository.model.Body;
import edu.pjatk.postman.repository.model.Header;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BodyRepository extends JpaRepository<Body,Long> {

    @Query("SELECT b from Body b where b.requestId=?1")
    Optional<Body> getBodyByRequestId(Long requestId);
}
