package edu.pjatk.postman.db.controller.param;

import edu.pjatk.postman.db.controller.param.model.*;
import edu.pjatk.postman.db.repository.model.Param;
import edu.pjatk.postman.db.repository.model.Request;
import edu.pjatk.postman.db.service.ParamService;
import edu.pjatk.postman.db.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

/**
 * @author Igor Motowidło (gottomy2)
 * Complete API for param entities
 */

@RestController
@RequestMapping("/param")
public class ParamController {
    private ParamService paramService;
    private RequestService requestService;

    @Autowired
    public ParamController(ParamService paramService) {
        this.paramService = paramService;
    }

    /**
     * Returns the Param Object by id.
     * @param id id of the Param Object.
     * @return Param Object
     */
    @GetMapping("/getParamById/{id}")
    public ResponseEntity<GetParamResponse> getParamById(@PathVariable("id") Long id){
        Optional<Param> param = paramService.getParamById(id);
        return param.map(value -> ResponseEntity.ok(new GetParamResponse(value.getId(),value.getRequestId(),value.getName(),value.getValue())))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Finds all Ids of existing params in the database.
     * @return List<Long> list of ids from the database.
     */
    @GetMapping("/getAllIds")
    public GetParamsResponses getParamsIds(){
        return new GetParamsResponses(paramService.getAllIds());
    }


    /**
     * Finds all existing params with specified requestId in the database
     * @param id - requestid to search for
     * @return Optional<Param> all params with specified requestId
     */
    @GetMapping("/getParamsByRequestId/{requestId}")
    public GetParamsByRequestIdResponse getParamsByRequestId(@PathVariable("requestId") Long id){
        return new GetParamsByRequestIdResponse(paramService.getParamsByRequestId(id));
    }

    /**
     * Creates new Param Entity in the database.
     * @param postParamRequest new Param parameters with values declared in body.
     * @return ResponseEntity.notFound() if param with such id already exists or there is no existing request with specified requestId | ResponseEntity.ok() on success.
     */
    @PostMapping("/createParam")
    public ResponseEntity<Void> createParam(@RequestBody PostParamRequest postParamRequest){
        Optional<Param> check = paramService.getParamById(postParamRequest.getId());
        Optional<Request> check2 = requestService.findRequestById(postParamRequest.getRequestId());
        if(check.isPresent() || check2.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        else{
            Param param = new Param(postParamRequest.getId(),postParamRequest.getRequestId(),postParamRequest.getName(),postParamRequest.getValue());
            paramService.createParam(param);
            return ResponseEntity.ok().build();
        }
    }

    /**
     * Updates Existing param in the database.
     * @param putParamRequest Param entity declared in body of PUT REQUEST
     * @return ResponseEntity.notFound() if entity does not exist or there is no existing request with specified requestId | ResponseEntity.ok() on success
     */
    @PutMapping("/updateParam")
    public ResponseEntity<Void> updateParam(@RequestBody PutParamRequest putParamRequest){
        Optional<Param> check = paramService.getParamById(putParamRequest.getId());
        Optional<Request> check2 = requestService.findRequestById(putParamRequest.getRequestId());
        if(check.isEmpty() || check2.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        else{
            Param param = new Param(putParamRequest.getId(),putParamRequest.getRequestId(),putParamRequest.getName(),putParamRequest.getValue());
            paramService.updateParam(param);
            return ResponseEntity.ok().build();
        }
    }

    /**
     * Removes existing Param entity from the database
     * @param id id of param to remove
     * @return ResponseEntity.notFound() if entity does not exist | ResponseEntity.ok() on success
     */
    @DeleteMapping("/deleteParam/{paramId}")
    public ResponseEntity<Void> deleteParam(@PathVariable("paramId") Long id){
        Optional<Param> param = paramService.getParamById(id);
        if(param.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        else{
            paramService.deleteParam(param.get());
            return ResponseEntity.ok().build();
        }
    }
}
