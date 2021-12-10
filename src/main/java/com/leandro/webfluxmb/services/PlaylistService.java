package com.leandro.webfluxmb.services;

import com.leandro.webfluxmb.models.Playlist;
import com.leandro.webfluxmb.repositories.PlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PlaylistService {

    @Autowired
    PlaylistRepository playlistRepository;

    public Flux<Playlist> findAll() {
    return playlistRepository.findAll();
    }

    public Mono<Playlist> findById(String id) {
        return playlistRepository.findById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    public  Mono<Playlist> save(Playlist playlist) {
       return playlistRepository.save(playlist);
    }
}
