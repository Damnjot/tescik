package org.cs.core;

import org.cs.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    UserService(UserRepository userRepository){
        this.userRepository= userRepository;
    }

    public Flux<User> getAllUsers(){
        return userRepository.findAll();
    }

    public Mono<User> getUser(int id){
        return userRepository.findById(id);
    }

    public Mono<User> addUser(User user){
        return userRepository.existsByUsername(user.getUsername()).flatMap(exists->trySaveUserIfDoesntExist(user, exists));
    }

    public Mono<User> trySaveUserIfDoesntExist(User user, boolean exists){
        if (exists)
            throw new RuntimeException("User witch such name already exists");
        else
           return userRepository.save(user);
    }

}
