package edu.pjatk.postman.db.controller.main;

import edu.pjatk.postman.db.repository.model.Param;
import edu.pjatk.postman.db.repository.model.Request;
import edu.pjatk.postman.db.service.ParamService;
import edu.pjatk.postman.db.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class MainController {
    ParamService paramService;
    RequestService requestService;

    @Autowired
    public MainController(ParamService paramService, RequestService requestService) {
        this.paramService = paramService;
        this.requestService = requestService;
    }

    public static <T> List<T> toList(Optional<T> opt) {
        return opt.isPresent()
                ? Collections.singletonList(opt.get())
                : Collections.emptyList();
    }


    /*
    User sends post request on localhost:9090/user/x where x is some user id
    He will recieve http status of the operation which is as follows:
    --> Find all requests where request.user_id = x
    --> Loop over requests List
    ---> Find all params of each request
    ---> Send request to API
    ---> Save response to database
    --> End Loop
    --> return status code;
    */
    // Template: ResponseEntity<C> (Class)
    @PostMapping("{userId}")
    public void getParams(@PathVariable("userId") Long user_id){
        Optional<Request> requestOptional = requestService.getRequestsByUserId(user_id);
        List<Request> requestList = toList(requestOptional);
        Long requestId;
        Optional <Param> paramsOptional;
        List<Param> paramsList;

        if(!requestList.isEmpty()){
            for (int i = 0; i < requestList.size(); i++) {
                requestId = requestList.get(i).getId();
                paramsOptional = paramService.findParamsByRequestId(requestId);
                paramsList = toList(paramsOptional);
                //TODO: wysłać request do api valentyna i zapisać response do bazy danych dla każdego requesta
            }
        }
    }
}
