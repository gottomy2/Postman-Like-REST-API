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

    @GetMapping("/getResponseById/{responseId}")
    public ResponseEntity<GetResponseResponse> getResponseById(@PathVariable("responseId") Long id){
        Optional<Response> response = responseService.getResponseById(id);
        return response.map(value -> ResponseEntity.ok(new GetResponseResponse(value.getId(),value.getRequestId(),value.getResponse())))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/getResponsesIds")
    public GetResponsesResponse getResponsedIds(){
        return new GetResponsesResponse(responseService.getAllIds());
    }

    @GetMapping("/getResponseByRequestId/{requestId}")
    public GetResponseByRequestId getResponseByRequestId(@PathVariable("requestId") Long id){
        return new GetResponseByRequestId(responseService.getResponseByRequestId(id));
    }

    @PostMapping("/createResponse")
    public ResponseEntity<Void> createResponse(@RequestBody PostResponseResponse postResponseResponse){
        Optional<Response> check = responseService.getResponseById(postResponseResponse.getId());
        Optional<Request> check2 = requestService.findRequestById(postResponseResponse.getId());
        if(check.isPresent() || check2.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        else{
            Response response = new Response(postResponseResponse.getId(),postResponseResponse.getRequestId(),postResponseResponse.getResponse());
            responseService.createResponse(response);
            return ResponseEntity.ok().build();
        }
    }

    @PutMapping("/updateResponse")
    public ResponseEntity<Void> updateResponse(@RequestBody PostResponseResponse postResponseResponse){
        Optional<Response> check = responseService.getResponseById(postResponseResponse.getId());
        Optional<Request> check2 = requestService.findRequestById(postResponseResponse.getId());
        if(check.isEmpty() || check2.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        else{
            Response response = new Response(postResponseResponse.getId(),postResponseResponse.getRequestId(),postResponseResponse.getResponse());
            responseService.updateResponse(response);
            return ResponseEntity.ok().build();
        }
    }

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
