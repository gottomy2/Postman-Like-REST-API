package edu.pjatk.postman.db.repository;

import edu.pjatk.postman.db.repository.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


//Create methods based on queries:
@Repository
public interface RequestRepository extends JpaRepository<Request,Long> {
    @Query("select r.id from Request r")
    List<Long> findId();

    @Query("select r.id from Request r")
    List<Long> findAllIds();

    @Query("select r from Request r where r.userId=?1")
    Optional<Request> requestList(Long id);
}
