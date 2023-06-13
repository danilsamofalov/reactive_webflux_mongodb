package com.example.testreactive;

import com.example.testreactive.model.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.function.client.WebClient;


@SpringBootApplication
public class TestReactiveApp implements CommandLineRunner {
    private static WebClient webClient = WebClient.create("http://localhost:4321");

    public static void main(String[] args) {
        SpringApplication.run(TestReactiveApp.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        getAllUsers();
    }

    private static void getAllUsers() {
        webClient.get()
                .uri("/api/users")
                .retrieve()
                .bodyToFlux(User.class)
                .doOnNext(user -> System.out.println("User from Flux ->" + user.getId()))
                .takeLast(1)
                .subscribe(user -> getUserById(user.getId()));
    }

    private static void getUserById(String userId) {
        webClient.get()
                .uri("/api/users/" + userId)
                .retrieve()
                .bodyToMono(User.class)
                .subscribe(user -> System.out.println("Mono " + user));
    }
}

