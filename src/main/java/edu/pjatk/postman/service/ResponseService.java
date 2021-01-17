package edu.pjatk.postman.service;

import edu.pjatk.postman.repository.ResponseRepository;
import edu.pjatk.postman.repository.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Igor Motowidło (gottomy2)
 * Simple Service for ResponseRepository class
 */
@Service
public class ResponseService {
    private ResponseRepository repository;

    @Autowired
    public ResponseService(ResponseRepository repository) {
        this.repository = repository;
    }

    /**
     * Finds Response Object with specified id
     * @param id id of the object we are looking for
     * @return Response Object
     */
    public Optional<Response> getResponseById(Long id){
        return repository.getResponseById(id);
    }

    public List<Long> getAllIds(){
        return repository.getAllIds();
    }

    /**
     * Finds all Response Object which have property request_id=requestId
     * @param requestId Id of the request by which we want to search Response table
     * @return Optional<Response> List of Response Objects
     */
    public Optional<Response> getResponseByRequestId(Long requestId){
        return repository.findResponseByRequestId(requestId);
    }

    /**
     * Creates Response Object on the database
     * @param response Response Object to create
     */
    public void createResponse(Response response){
        repository.save(response);
    }

    /**
     * If Response exists overrides it either way creates a new Response Object
     * @param response Response object to update/create
     */
    public void updateResponse(Response response){
        repository.save(response);
    }

    /**
     * Removes Response Object from the database
     * @param response Response Object to remove
     */
    public void deleteResponse(Response response){
        repository.delete(response);
    }
}
