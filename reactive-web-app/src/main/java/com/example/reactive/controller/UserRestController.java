package com.example.reactive.controller;


import com.example.reactive.model.User;
import com.example.reactive.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserRestController {
    private final UserRepository repository;

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<User> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Mono<User> findById(@PathVariable String id) {
        return repository.findById(id);
    }

}
