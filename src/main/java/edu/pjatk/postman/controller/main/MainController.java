package edu.pjatk.postman.controller.main;

import edu.pjatk.postman.Helper;
import edu.pjatk.postman.repository.model.Request;
import edu.pjatk.postman.service.ParamService;
import edu.pjatk.postman.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author Igor Motowidło (gottomy2)
 * Main Controller, contains all the logics.
 * Its Task is to send all Requests with their Parameters and add Responses to the database
 * TODO: Finish the logics based on PostmanCopy URL granted by Valentyn Kuts.
 */
@RestController
@RequestMapping("/main")
public class MainController {
    ParamService paramService;
    RequestService requestService;

    @Autowired
    public MainController(ParamService paramService, RequestService requestService) {
        this.paramService = paramService;
        this.requestService = requestService;
    }


    // Template: ResponseEntity<C> (Class)
    @PostMapping("test/userId/{userId}")
    public void getParamsByUserId(@PathVariable("userId") Long user_id){
        Optional<Request> requestOptional = requestService.getRequestsByUserId(user_id);
        List<Request> requestList = Helper.toList(requestOptional);
//        Param[] paramList = paramService.getParamsByRequestId(user_id);

        for(int i=0;i<requestList.size();i++){
            Request request = requestList.get(i);
//            Param param = paramList[i];
        }
    }
}
