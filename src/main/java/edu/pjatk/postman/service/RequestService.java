package edu.pjatk.postman.service;

import edu.pjatk.postman.repository.RequestRepository;
import edu.pjatk.postman.repository.model.Request;
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

    public List<Long> findAllRequests(){
        return repository.findAllIds();
    }

    public Optional<Request> getRequestsByUserId(Long id){
        return repository.requestList(id);
    }

    public List<Long> findAllIds(){
        return repository.findId();
    }

    public Optional<Request> findRequestById(Long id){
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
