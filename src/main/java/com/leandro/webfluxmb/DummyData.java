package com.leandro.webfluxmb;

import com.leandro.webfluxmb.models.Playlist;
import com.leandro.webfluxmb.repositories.PlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public class DummyData implements CommandLineRunner {

    @Autowired
    private PlaylistRepository repository;

    @Override
    public void run(String... args) throws Exception {
       repository.findAll().switchIfEmpty(
               Flux.just("Java", "C#", "C++", "PHP", "NodeJs")
                       .map(nome -> new Playlist(nome))
                       .flatMap(repository::save))
               .subscribe(System.out::println);
    }
}
