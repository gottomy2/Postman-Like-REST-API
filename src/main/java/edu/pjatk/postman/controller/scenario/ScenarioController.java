package edu.pjatk.postman.controller.scenario;

import edu.pjatk.postman.controller.scenario.model.*;
import edu.pjatk.postman.repository.model.Scenario;
import edu.pjatk.postman.service.ScenarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

/**
 * @author Igor Motowidło (gottomy2)
 * Complete API for scenario entities
 */

@RestController
@RequestMapping("/scenario")
public class ScenarioController {
    private final ScenarioService scenarioService;
    // Removed since validation is now on database side:
    // private UserService userService;
    // private RequestService requestService;



    @Autowired
    public ScenarioController(ScenarioService scenarioService) {
        this.scenarioService = scenarioService;
    }

    /**
     * Returns Scenario Object based on template from GetScenarioResponse
     * @param id id of the scenario to search for
     * @return STATUS CODE: 200 && found user entity on success | STATUS CODE: 404 and empty body
     */
    @GetMapping("/getScenarioById/{id}")
    public ResponseEntity<GetScenarioResponse> getScenario(@PathVariable("id") Long id){
        Optional<Scenario> scenario = scenarioService.getScenarioById(id);
        return scenario.map(value -> ResponseEntity.ok(new GetScenarioResponse(value.getId(),value.getUserId(),value.getRequestIds(),value.getName(),value.getDescription())))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Finds all Scenarios based on userId
     * @param id id of the user to search scenarios table for
     * @return STATUS CODE: 200 && Optional of Scenarios with userId=id || STATUS CODE: 404 && Empty Body on failure.
     */
    @GetMapping("/getScenarioByUserId/{userId}")
    public ResponseEntity<GetScenarioByUserIdResponse> getScenarioByUserId(@PathVariable("userId") Long id){
        Optional<Scenario> scenarioList = scenarioService.getScenarioById(id);
        if(scenarioList.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        else{
            return ResponseEntity.ok(new GetScenarioByUserIdResponse(scenarioList));
        }
    }

    /**
     * @return STATUS CODE: 200 && Optional of all ids from the scenarios table if any exists || STATUS CODE: 404 on failure.
     */
    @GetMapping("/getAllIds/")
    public ResponseEntity<GetScenarioAllIdsResponse> getAllIds(){
        List<Long> idList= scenarioService.getAllScenarioIds();
        if(idList.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        else{
            return ResponseEntity.ok(new GetScenarioAllIdsResponse(idList));
        }
    }

    /**
     * Creates new Scenario in the database.
     * @param postScenario Template of Scenario object to pass in request body. Can be found within PostScenarioRequest class.
     * @return STATUS CODE: 201 on created && scenario entity in the body || STATUS CODE: 404 and empty body on failure.
     */
    @PostMapping("/createScenario")
    public ResponseEntity<Void> createScenario(@RequestBody PostScenarioRequest postScenario){
//        Optional<User> user = userService.findUserById(postScenario.getUserId());
//        Optional<Scenario> check = scenarioService.getScenarioById(postScenario.getId());
//        if(user.isPresent()){
//            String[] requestIds = postScenario.getRequestIds().split("/");
//            List<Request> existingRequests = new ArrayList<>();
//
//            for (String requestId : requestIds) {
//                Optional<Request> request = requestService.findRequestById(Long.parseLong(requestId));
//                request.ifPresent(existingRequests::add);
//            }
//
//            if(requestIds.length==existingRequests.size()){
//                if(check.isPresent()){
//                    return ResponseEntity.notFound().build();
//                }
//                else{
//        System.out.println(postScenario);
        Scenario scenario = new Scenario(postScenario.getUserId(),
                postScenario.getRequestIds(), postScenario.getName(), postScenario.getDescription());
        System.out.println(scenario);
        scenarioService.createScenario(scenario);
        return ResponseEntity.created(URI.create("http://localhost:9090/scenario/getScenarioById/" + scenario.getId())).build();
//                }
//            }
//        }
//        return ResponseEntity.notFound().build();
    }

    /**
     * Updates existing scenario entity in the database.
     * @param putScenario Scenario object passed in Request Body matching the template of PutScenarioRequest.class
     * @return STATUS CODE: 200 on created || STATUS CODE: 404 on failure.
     */
    @PutMapping("/updateScenario")
    public ResponseEntity<Void> updateScenario(@RequestBody PutScenarioRequest putScenario){
        Optional<Scenario> check = scenarioService.getScenarioById(putScenario.getId());
        if(check.isPresent()){
            return ResponseEntity.notFound().build();
        }
        Scenario scenario = new Scenario(putScenario.getId(), putScenario.getUserId(),
                putScenario.getRequestIds(), putScenario.getName(), putScenario.getDescription());
        scenarioService.updateScenario(scenario);
        return ResponseEntity.ok().build();
    }

    /**
     * Deletes existing scenario entity from the database.
     * @param id id of scenario to remove from the database
     * @return STATUS CODE: 200 on update || STATUS CODE: 404 on failure.
     */
    @DeleteMapping("/deleteScenario/{id}")
    public ResponseEntity<Void> deleteScenario(@PathVariable("id") Long id){
        Optional<Scenario> scenario = scenarioService.getScenarioById(id);
        if(scenario.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        else{
            scenarioService.deleteScenario(scenario.get());
            return ResponseEntity.ok().build();
        }
    }
}
