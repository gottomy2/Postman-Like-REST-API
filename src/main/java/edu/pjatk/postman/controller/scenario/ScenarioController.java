package edu.pjatk.postman.controller.scenario;

import edu.pjatk.postman.controller.scenario.model.GetScenarioAllIdsResponse;
import edu.pjatk.postman.controller.scenario.model.GetScenarioByUserIdResponse;
import edu.pjatk.postman.controller.scenario.model.GetScenarioResponse;
import edu.pjatk.postman.controller.scenario.model.PostScenarioRequest;
import edu.pjatk.postman.repository.model.Scenario;
import edu.pjatk.postman.service.RequestService;
import edu.pjatk.postman.service.ScenarioService;
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
    private RequestService requestService;
    private ScenarioService scenarioService;

    @GetMapping("/getScenarioById/{id}")
    public ResponseEntity<GetScenarioResponse> getScenario(@PathVariable("id") Long id){
        Optional<Scenario> scenario = scenarioService.getScenarioById(id);
        return scenario.map(value -> ResponseEntity.ok(new GetScenarioResponse(value.getId(),value.getUserId(),value.getRequestIds(),value.getName(),value.getDescription())))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

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

    //TODO: add conditions based on if ids from requestIds strings are exsting in the database && userId conditions
    @PostMapping("/createScenario")
    public ResponseEntity<Void> createScenario(@RequestBody PostScenarioRequest postScenario){
        Optional<Scenario> check = scenarioService.getScenarioById(postScenario.getId());
        if(check.isPresent()){
            return ResponseEntity.notFound().build();
        }
        else{
            Scenario scenario = new Scenario(postScenario.getId(), postScenario.getUserId(),
                    postScenario.getRequestIds(), postScenario.getName(), postScenario.getDescription());

            scenarioService.createScenario(scenario);
            return ResponseEntity.created(URI.create("https://localhost:9090/scenario/getScenarioById/" + scenario.getId())).build();
        }
    }

    //TODO: add conditions based on if ids from requestIds strings are exsting in the database && userId conditions
    @PutMapping("/updateScenario")
    public ResponseEntity<Void> updateScenario(@RequestBody PostScenarioRequest putScenario){
        Optional<Scenario> check = scenarioService.getScenarioById(putScenario.getId());
        if(check.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        else{
            Scenario scenario = new Scenario(putScenario.getId(), putScenario.getUserId(),
                    putScenario.getRequestIds(), putScenario.getName(), putScenario.getDescription());
            scenarioService.updateScenario(scenario);
            return ResponseEntity.ok().build();
        }
    }

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
