package edu.pjatk.postman.db.service;

import edu.pjatk.postman.db.repository.UserRepository;
import edu.pjatk.postman.db.repository.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> findAllUsers(){
        return repository.findAll();
    }

    public Long findUserByName(String name){
        return repository.findIdByName(name);
    }

    public List<Long> findAllIds(){
        return repository.findId();
    }

    public Optional<User> findUser(Long id){
        return repository.findUserById(id);
    }

    public void createUser(User user){
        repository.save(user);
    }

    public void updateUser(User user){
        repository.save(user);
    }

    public void deleteUser(User user){
        repository.delete(user);
    }
}