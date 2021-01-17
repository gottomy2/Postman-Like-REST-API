package edu.pjatk.postman.db.service;

import edu.pjatk.postman.db.repository.ParamRepository;
import edu.pjatk.postman.db.repository.model.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author: Igor Motowidło (gottomy2)
 * Simple Service for ParamRepository class
 */

@Service
public class ParamService {
    private ParamRepository repository;

    @Autowired
    public ParamService(ParamRepository paramRepository) {
        this.repository = paramRepository;
    }

    /**
     * Finds Param Object by specified id
     * @param id id of the Param Object we're looking for
     * @return Param Object
     */
    public Optional<Param> getParamById(Long id){
        return repository.getParamById(id);
    }

    /**
     * Finds all Params with specified request_id = id
     * @param id id of the Request to which Params belong
     * @return Optional<Param>
     */
    public Optional<Param> getParamsByRequestId(Long id){
        return repository.findParamsByRequestId(id);
    }

    public List<Long> getAllIds(){
        return repository.getAllIds();
    }

    /**
     * Creates new Param object in the database
     * @param param Param object to create
     */
    public void createParam(Param param){
        repository.save(param);
    }

    /**
     * Updates existing Param in the database if it doesn't exist creates new Param.
     * @param param Param Object to update / create
     */
    public void updateParam(Param param){
        repository.save(param);
    }

    /**
     * Removes Param Object from the database
     * @param param Param Object to remove
     */
    public void deleteParam(Param param){
        repository.delete(param);
    }
}
