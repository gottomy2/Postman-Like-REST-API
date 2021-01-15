package edu.pjatk.postman.db.controller.param;

import edu.pjatk.postman.db.repository.model.Param;
import edu.pjatk.postman.db.service.ParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Igor Motowidło (gottomy2)
 * TODO: Check how to solve the problem which occures in createParam.
 */

@RestController
@RequestMapping("/param")
public class ParamController {
    private ParamService paramService;

    @Autowired
    public ParamController(ParamService paramService) {
        this.paramService = paramService;
    }

    /**
     * Returns the Param Object by id.
     * @param id id of the Param Object.
     * @return Param Object
     */
    @GetMapping("/getById/{id}")
    public Param getParamById(@PathVariable("id") Long id){
        return paramService.getParamById(id);
    }

    @GetMapping("/getByRequestId")

    /**
     * Creates new Param at database
     * @param requestId ID of request entity which param relies on
     * @param name Name of the parameter | example: "username"
     * @param value Value of the parameter | example: "admin"
     */
    @PostMapping("/requestId/{request_id}/name/{name}/value/{value}")
    public void createParam(@PathVariable("request_id") Long requestId,@PathVariable("name") String name, @PathVariable("value") String value){
        Param param = new Param();
        param.setName(name);
//        param.setRequest(requestId);
        param.setValue(value);
        paramService.createParam(param);
    }


}
