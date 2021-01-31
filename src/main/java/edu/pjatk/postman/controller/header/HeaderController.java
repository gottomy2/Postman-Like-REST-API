package edu.pjatk.postman.controller.header;

import edu.pjatk.postman.controller.header.model.GetHeaderResponse;
import edu.pjatk.postman.controller.header.model.PostHeaderRequest;
import edu.pjatk.postman.controller.header.model.PutHeaderRequest;
import edu.pjatk.postman.repository.model.Header;
import edu.pjatk.postman.repository.model.Request;
import edu.pjatk.postman.service.HeaderService;
import edu.pjatk.postman.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/header")
public class HeaderController {
    private final HeaderService headerService;
    private final RequestService requestService;

    @Autowired
    public HeaderController(HeaderService headerService,RequestService requestService) {
        this.headerService = headerService;
        this.requestService=requestService;
    }

    @GetMapping("/getHeader/{id}")
    public ResponseEntity<GetHeaderResponse> getHeader(@PathVariable("id") Long id){
        Optional<Header> headerOptional = headerService.getHeaderById(id);
        return headerOptional.map(value -> ResponseEntity.ok(new GetHeaderResponse(value.getId(),value.getRequestId(), value.getName(), value.getValue())))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/createHeader")
    public ResponseEntity<Void> createHeader(@RequestBody PostHeaderRequest postHeaderRequest){
        Optional<Request> requestOptional = requestService.findRequestById(postHeaderRequest.getRequestId());
        if(requestOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        else{
            Header header = new Header(postHeaderRequest.getRequestId(), postHeaderRequest.getName(), postHeaderRequest.getValue());
            headerService.createHeader(header);
            return ResponseEntity.created(URI.create("https://localhost:9090/header/getHeader/" + header.getId())).build();
        }
    }

    @PutMapping("/updateHeader")
    public ResponseEntity<Void> updateHeader(@RequestBody PutHeaderRequest request){
        Optional<Request> requestOptional = requestService.findRequestById(request.getRequestId());
        if(requestOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        else{
            Optional<Header> headerOptional = headerService.getHeaderById(request.getId());
            if(headerOptional.isEmpty()){
                return ResponseEntity.notFound().build();
            }
            else{
                headerService.updateHeader(headerOptional.get());
                return ResponseEntity.ok().build();
            }
        }
    }

    @DeleteMapping("/deleteHeader/{id}")
    public ResponseEntity<Void> deleteHeader(@PathVariable("id") Long id){
        Optional<Header> headerOptional = headerService.getHeaderById(id);
        if(headerOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        else{
            headerService.deleteHeader(headerOptional.get());
            return ResponseEntity.ok().build();
        }
    }
}
