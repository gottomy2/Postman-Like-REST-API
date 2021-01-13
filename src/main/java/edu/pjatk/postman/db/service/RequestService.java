package edu.pjatk.postman.db.service;

import edu.pjatk.postman.db.repository.RequestRepository;
import edu.pjatk.postman.db.repository.model.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


//create methods based on ID:
@Service
public class RequestService {
    private RequestRepository repository;

    @Autowired
    public RequestService(RequestRepository repository) {
        this.repository = repository;
    }

    public List<Request> findAllRequests(){
        return repository.findAll();
    }

    public List<Long> findAllIds(){
        return repository.findId();
    }

    public Optional<Request> findRequest(Long id){
        return repository.findById(id);
    }

    public void createRequest(Request request){
        repository.save(request);
    }

    public void updateRequest(Request request){
        repository.save(request);
    }

    public void deleteRequest(Request request){
        repository.delete(request);
    }

}
