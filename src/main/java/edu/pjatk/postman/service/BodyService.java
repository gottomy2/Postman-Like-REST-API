package edu.pjatk.postman.service;

import edu.pjatk.postman.repository.BodyRepository;
import edu.pjatk.postman.repository.model.Body;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BodyService {
    private BodyRepository bodyRepository;

    @Autowired
    public BodyService(BodyRepository bodyRepository) {
        this.bodyRepository = bodyRepository;
    }

    public Optional<Body> getBodyById(Long id){
        return bodyRepository.findById(id);
    }

    public List<Body> getBodyByRequestId(Long requestId){
        return bodyRepository.getBodyByRequestId(requestId);
    }

    public void createBody(Body body){
        bodyRepository.save(body);
    }

    public void updateBody(Body body){
        bodyRepository.save(body);
    }

    public void deleteBody(Body body){
        bodyRepository.delete(body);
    }
}
