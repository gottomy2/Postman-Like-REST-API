package edu.pjatk.postman.service;

import edu.pjatk.postman.repository.ScenarioRepository;
import edu.pjatk.postman.repository.model.Scenario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScenarioService {
    private ScenarioRepository repository;

    @Autowired
    public ScenarioService(ScenarioRepository scenarioRepository) {
        this.repository = scenarioRepository;
    }

    public List<Long> getAllScenarioIds(){
        return repository.findAllIds();
    }

    public Optional<Scenario> getScenarioById(Long id){
        return repository.findScenarioById(id);
    }

    public Optional<Scenario>  getScenarioByUserId(Long id){
        return repository.findScenariosByUserId(id);
    }

    public void createScenario(Scenario scenario){
        repository.save(scenario);
    }

    public void updateScenario(Scenario scenario){
        repository.save(scenario);
    }

    public void deleteScenario(Scenario scenario){
        repository.delete(scenario);
    }
}
