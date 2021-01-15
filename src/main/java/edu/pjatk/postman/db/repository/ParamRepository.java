package edu.pjatk.postman.db.repository;

import edu.pjatk.postman.db.repository.model.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ParamRepository extends JpaRepository<Param,Long> {
    @Query("select p.id from Param p")
    List<Long> findId();

    @Query("select p from Param p where p.request=?1")
    Optional<Param> findParamsByRequestId(Long id);
}
