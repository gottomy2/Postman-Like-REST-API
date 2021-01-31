package edu.pjatk.postman.controller.body;

import edu.pjatk.postman.controller.body.model.GetBodyResponse;
import edu.pjatk.postman.controller.body.model.PostBodyRequest;
import edu.pjatk.postman.controller.body.model.PutBodyRequest;
import edu.pjatk.postman.repository.model.Body;
import edu.pjatk.postman.repository.model.Request;
import edu.pjatk.postman.service.BodyService;
import edu.pjatk.postman.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/body")
public class BodyController {
    private final BodyService bodyService;
    private final RequestService requestService;

    @Autowired
    public BodyController(BodyService bodyService,RequestService requestService) {
        this.bodyService = bodyService;
        this.requestService=requestService;
    }

    @GetMapping("/getBody/{id}")
    public ResponseEntity<GetBodyResponse> getBodyById(@PathVariable("id") Long id){
        Optional<Body> body = bodyService.getBodyById(id);
        return body.map(value -> ResponseEntity.ok(new GetBodyResponse(value.getId(),value.getRequestId(),value.getName(),value.getValue())))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/createBody")
    public ResponseEntity<Void> createBody(@RequestBody PostBodyRequest postBodyRequest){
        Optional<Request> requestOptional = requestService.findRequestById(postBodyRequest.getRequestId());
        if(requestOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        else{
            Body body = new Body(postBodyRequest.getRequestId(),postBodyRequest.getName(),postBodyRequest.getValue());
            bodyService.createBody(body);
            return ResponseEntity.created(URI.create("http://localhost:9090/body/getBody/"+body.getId())).build();
        }
    }

    @PutMapping("/updateBody")
    public ResponseEntity<Void> updateBody(@RequestBody PutBodyRequest putBodyRequest){
        Optional<Request> requestOptional = requestService.findRequestById(putBodyRequest.getRequestId());
        if(requestOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        else{
            Optional<Body> bodyOptional = bodyService.getBodyById(putBodyRequest.getId());
            if(bodyOptional.isEmpty()){
                return ResponseEntity.notFound().build();
            }
            else {
                Body body = new Body(putBodyRequest.getId(), putBodyRequest.getRequestId(), putBodyRequest.getName(), putBodyRequest.getValue());
                bodyService.updateBody(body);
                return ResponseEntity.ok().build();
            }
        }
    }

    @DeleteMapping("/deleteBody/{id}")
    public ResponseEntity<Void> deleteBody(@PathVariable("id") Long id){
        Optional<Body> bodyOptional = bodyService.getBodyById(id);
        if(bodyOptional.isPresent()){
            bodyService.deleteBody(bodyOptional.get());
            return ResponseEntity.ok().build();
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
}
