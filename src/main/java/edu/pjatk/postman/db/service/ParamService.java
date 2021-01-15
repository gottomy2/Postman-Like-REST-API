package edu.pjatk.postman.db.service;

import edu.pjatk.postman.db.repository.ParamRepository;
import edu.pjatk.postman.db.repository.model.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ParamService {
    private ParamRepository paramRepository;

    @Autowired
    public ParamService(ParamRepository paramRepository) {
        this.paramRepository = paramRepository;
    }

    public Optional<Param> findParamsByRequestId(Long id){
        return paramRepository.findParamsByRequestId(id);
    }

    public void createUser(Param param){
        paramRepository.save(param);
    }

    public void updateUser(Param param){
        paramRepository.save(param);
    }

    public void deleteUser(Param param){
        paramRepository.delete(param);
    }
}
