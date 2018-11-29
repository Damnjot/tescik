package org.cs.core;

import org.cs.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class UserController {

    private UserService userService;

    @Autowired
    UserController(UserService userService){
        this.userService=userService;
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public Mono<User> addUser(String username, String password){
        return userService.addUser(new User(username, password));
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public Mono<User> getUser(@PathVariable int id){
        return userService.getUser(id);
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public Flux<User>  getAllUsers(){
        return userService.getAllUsers();
    }
}
