package edu.pjatk.postman.service;

import edu.pjatk.postman.repository.RequestRepository;
import edu.pjatk.postman.repository.model.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


/**
 * @author Igor Motowidło (gottomy2)
 * Simple Service for ParamRepository class
 */
@Service
public class RequestService {
    private RequestRepository repository;

    @Autowired
    public RequestService(RequestRepository repository) {
        this.repository = repository;
    }

    /**
     * @return List<Long> of all Ids within Request table
     */
    public List<Long> findAllRequests(){
        return repository.findAllIds();
    }

    /**
     * @param id - id of user to search by request table by
     * @return Optional<Request> all requests with request.userId=id
     */
    public Optional<Request> getRequestsByUserId(Long id){
        return repository.requestList(id);
    }

    /**
     * @param id to search request table for.
     * @return Optional<Request> single request record where request.id=id
     */
    public Optional<Request> findRequestById(Long id){
        return repository.findById(id);
    }

    /**
     * Creates new request object on the database
     * @param request request to add to the database
     */
    public void createRequest(Request request){
        repository.save(request);
    }

    /**
     * Updates existing request on the database
     * @param request request to update on the database
     */
    public void updateRequest(Request request){
        repository.save(request);
    }

    /**
     * Removes existing request from the database
     * @param request request to delete
     */
    public void deleteRequest(Request request){
        repository.delete(request);
    }

}
