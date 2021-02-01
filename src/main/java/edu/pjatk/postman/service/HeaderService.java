package edu.pjatk.postman.service;

import edu.pjatk.postman.repository.HeaderRepository;
import edu.pjatk.postman.repository.model.Header;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HeaderService {
    private final HeaderRepository headerRepository;

    @Autowired
    public HeaderService(HeaderRepository headerRepository) {
        this.headerRepository = headerRepository;
    }

    public Optional<Header> getHeaderById(Long id){
        return headerRepository.findById(id);
    }

    public List<Header> getHeadersByRequestId(Long requestId){
        return headerRepository.getHeadersByRequestId(requestId);
    }

    public void createHeader(Header header){
        headerRepository.save(header);
    }

    public void updateHeader(Header header){
        headerRepository.save(header);
    }

    public void deleteHeader(Header header){
        headerRepository.delete(header);
    }
}
