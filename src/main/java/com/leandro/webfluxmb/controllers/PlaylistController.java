package com.leandro.webfluxmb.controllers;

import com.leandro.webfluxmb.models.Playlist;
import com.leandro.webfluxmb.services.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.Duration;

@RestController
@RequestMapping("/api/v1/playlist")
public class PlaylistController {

    @Autowired
    private PlaylistService playlistService;

    @GetMapping()
    public Flux<Playlist> getAll() {
        return playlistService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Playlist> getById(@PathVariable String id) {
        return playlistService.findById(id)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Não Encontrado")));
    }

    @PostMapping("/salvar")
    public Mono<Playlist> savePlaylist(@RequestBody Playlist playlist) {
        return playlistService.save(playlist);
    }

    @GetMapping(value = "/eventos", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Tuple2<Long, Playlist>> getEvents() {
     Flux<Long> interval = Flux.interval(Duration.ofSeconds(5));
     Flux<Playlist> events = playlistService.findAll();
     return Flux.zip(interval, events);
    }
}
