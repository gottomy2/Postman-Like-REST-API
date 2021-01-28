package edu.pjatk.postman.repository;

import edu.pjatk.postman.repository.model.Header;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HeaderRepository extends JpaRepository<Header,Long> {

    @Query("SELECT h FROM Header h where h.requestId=?1")
    Optional<Header> getHeadersByRequestId(Long requestId);
}
