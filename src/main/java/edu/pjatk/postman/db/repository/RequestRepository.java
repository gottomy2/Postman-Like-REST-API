package edu.pjatk.postman.db.repository;

import edu.pjatk.postman.db.repository.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


//Create methods based on queries:
@Repository
public interface RequestRepository extends JpaRepository<Request,Long> {
    @Query("select r.id from Request r")
    List<Long> findId();
}
