package org.cs.core;

import org.cs.domain.User;
import org.cs.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;

import static org.springframework.web.reactive.function.BodyInserters.fromObject;
import static org.springframework.web.reactive.function.server.ServerResponse.*;

@Component
public class UserHandler {

    private UserService userService;

    @Autowired
    UserHandler(UserService userService){
        this.userService = userService;
    }

    public Mono<ServerResponse> addUser(ServerRequest request){

        return request
                .bodyToMono(User.class)
                .flatMap(userService::addUser)
                .flatMap(user -> created(constructResourceURI(request, user)).build())
                .switchIfEmpty(badRequest().build())
                .onErrorResume(ResponseUtils::handleReactiveError);

    }

    public Mono<ServerResponse> getUser(ServerRequest request){
        return request
                .bodyToMono(Integer.class)
                .flatMap(userService::getUser)
                .flatMap(user -> ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(fromObject(user))
                )
                .switchIfEmpty(notFound().build());
    }

    public Mono<ServerResponse> getAllUsers(ServerRequest request){
        return ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(userService.getAllUsers(), User.class);
    }

    private URI constructResourceURI(ServerRequest request, User user) {
        return request.uri().resolve("/" + user.getId());
    }

}
