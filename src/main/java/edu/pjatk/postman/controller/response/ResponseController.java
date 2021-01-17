package edu.pjatk.postman.controller.response;

import edu.pjatk.postman.controller.response.model.GetResponseByRequestId;
import edu.pjatk.postman.controller.response.model.GetResponseResponse;
import edu.pjatk.postman.controller.response.model.GetResponsesResponse;
import edu.pjatk.postman.controller.response.model.PostResponseResponse;
import edu.pjatk.postman.repository.model.Request;
import edu.pjatk.postman.repository.model.Response;
import edu.pjatk.postman.service.RequestService;
import edu.pjatk.postman.service.ResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @author Igor Motowidło (gottomy2)
 * Complete API for response entities
 */

@RestController
@RequestMapping("/response")
public class ResponseController {
    private ResponseService responseService;
    private RequestService requestService;

    @Autowired
    public ResponseController(ResponseService responseService) {
        this.responseService = responseService;
    }

    /**
     * Finds response in the database by id
     * @param id id of the response to find in database
     * @return if response exists returns GetResponseResponse object | if response does not exist returns ResponseEntity.notFound()
     */
    @GetMapping("/getResponseById/{responseId}")
    public ResponseEntity<GetResponseResponse> getResponseById(@PathVariable("responseId") Long id){
        Optional<Response> response = responseService.getResponseById(id);
        return response.map(value -> ResponseEntity.ok(new GetResponseResponse(value.getId(),value.getRequestId(),value.getResponse())))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Finds all ids of responses in the database
     * @return all Ids of responses from the database
     */
    @GetMapping("/getResponsesIds")
    public GetResponsesResponse getResponsedIds(){
        return new GetResponsesResponse(responseService.getAllIds());
    }

    /**
     * Finds all responses with Response.requestId=id
     * @param id id to search responses table for
     * @return GetResponseByRequestId Object
     */
    @GetMapping("/getResponseByRequestId/{requestId}")
    public GetResponseByRequestId getResponseByRequestId(@PathVariable("requestId") Long id){
        return new GetResponseByRequestId(responseService.getResponseByRequestId(id));
    }

    /**
     * Creates new Response object on the database (adds new response to the database)
     * @param request PostResponseResponse Object
     * @return ResponseEntity.notFound().build() if Response already exists or request with such requestId does not exist | ResponseEntity.ok() on success
     */
    @PostMapping("/createResponse")
    public ResponseEntity<Void> createResponse(@RequestBody PostResponseResponse request){
        Optional<Response> check = responseService.getResponseById(request.getId());
        Optional<Request> check2 = requestService.findRequestById(request.getId());
        if(check.isPresent() || check2.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        else{
            Response response = new Response(request.getId(),request.getRequestId(),request.getResponse());
            responseService.createResponse(response);
            return ResponseEntity.ok().build();
        }
    }

    /**
     * Updates Existing Response object on the database (adds new response to the database)
     * @param request PostResponseResponse Object
     * @return ResponseEntity.notFound() if response does not exist or if request with provided requestId does not exist | ResponseEntity.ok() on success
     */
    @PutMapping("/updateResponse")
    public ResponseEntity<Void> updateResponse(@RequestBody PostResponseResponse request){
        Optional<Response> check = responseService.getResponseById(request.getId());
        Optional<Request> check2 = requestService.findRequestById(request.getId());
        if(check.isEmpty() || check2.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        else{
            Response response = new Response(request.getId(),request.getRequestId(),request.getResponse());
            responseService.updateResponse(response);
            return ResponseEntity.ok().build();
        }
    }

    /**
     * Removes Response entity with specified id from the database
     * @param id id of the Response entity to remove
     * @return ResponseEntity.notFound() if entity with specified id does not exist | ResponseEntity.ok() on success
     */
    @DeleteMapping("/deleteResponse/responseId")
    public ResponseEntity<Void> deleteResponse(@PathVariable("responseId") Long id){
        Optional<Response> response = responseService.getResponseById(id);
        if(response.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        else{
            responseService.deleteResponse(response.get());
            return ResponseEntity.ok().build();
        }
    }
}