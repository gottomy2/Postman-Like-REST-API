package edu.pjatk.postman.controller.request;

import edu.pjatk.postman.controller.request.model.GetRequestResponse;
import edu.pjatk.postman.controller.request.model.GetRequestsResponses;
import edu.pjatk.postman.controller.request.model.PostRequestResponse;
import edu.pjatk.postman.repository.model.Param;
import edu.pjatk.postman.repository.model.Request;
import edu.pjatk.postman.service.ParamService;
import edu.pjatk.postman.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

/**
 * @author Igor Motowidło (gottomy2)
 * Complete API for request entities
 */

@RestController
@RequestMapping("/request")
public class RequestController {
    private RequestService requestService;
    private ParamService paramService;

    @Autowired
    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    /**
     * Finds Request by its id in the database.
     * @param id - id of the request to find
     * @return ResponseEntity.ok(RequestId,RequestUserId,RequestUrl) on success | ResponseEntity.notFound() on failure.
     */
    @GetMapping("/getRequestById/{requestId}")
    public ResponseEntity<GetRequestResponse> getRequest(@PathVariable("requestId") Long id) {
        Optional<Request> request = requestService.findRequestById(id);
        return request.map(value -> ResponseEntity.ok(new GetRequestResponse(value.getId(),value.getUserId(),value.getUrl())))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Finds ids of all requests in the database and returns a list of Longs
     * @return List of all Long ids from database
     */
    @GetMapping("/getAllIds")
    public GetRequestsResponses getAllRequestsIds(){
        return new GetRequestsResponses(requestService.findAllRequests());
    }

    /**
     * Creates new Request entity in the database
     * @param postRequestRequest new request parameters values declared in body.
     * @return ResponseEntity.notFound on failure | ResponseEntity.created on success with API call of getRequest() method.
     */
    @PostMapping("/createRequest")
    public ResponseEntity<Void> createRequest(@RequestBody PostRequestResponse postRequestRequest){
        Optional<Request> check = requestService.findRequestById(postRequestRequest.getId());
        if(check.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        else{
            Request request = new Request(postRequestRequest.getUserId(),postRequestRequest.getUserId(),postRequestRequest.getUrl());
            requestService.createRequest(request);
            return ResponseEntity.created(URI.create("http://localhost:8080/request/getRequestById/"+request.getId())).build();
        }
    }

    /**
     * Updates existing request in the database
     * @param postRequestRequest request entity declared in body of Put REQUEST
     * @return ResponseEntity.notFound() on non existing entity | ResponseEntity.ok() on success
     */
    @PutMapping("/updateRequest")
    public ResponseEntity<Void> updateRequest(@RequestBody PostRequestResponse postRequestRequest){
        Optional<Request> request = requestService.findRequestById(postRequestRequest.getId());
        if(request.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        else{
            request.get().setId(postRequestRequest.getId());
            request.get().setUrl(postRequestRequest.getUrl());
            request.get().setUserId(postRequestRequest.getUserId());
            requestService.updateRequest(request.get());
            return ResponseEntity.ok().build();
        }
    }

    /**
     * Removes existing Request entity from the database
     * @param id id of request to remove
     * @return ResponseEntity.notFound() if entity does not exist | ResponseEntity.ok() on success
     */
    @DeleteMapping("/deleteRequest/{requestId}")
    public ResponseEntity<Void> deleteRequest(@PathVariable("requestId") Long id){
        Optional<Request> request = requestService.findRequestById(id);
        if(request.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        else{
            requestService.deleteRequest(request.get());
            Optional<Param> param = paramService.getParamsByRequestId(id);
            if(param.isPresent()){
                paramService.deleteParam(param.get());
            }
            return ResponseEntity.ok().build();
        }
    }

}
