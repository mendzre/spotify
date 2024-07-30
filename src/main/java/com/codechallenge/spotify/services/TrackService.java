package com.codechallenge.spotify.services;

import com.codechallenge.spotify.exceptions.ResourceAlreadyExists;
import com.codechallenge.spotify.models.TrackEntity;
import com.codechallenge.spotify.repositories.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrackService {
    @Autowired
    private TrackRepository trackRepository;

    public TrackEntity getByIsrc(String isrc) {
        return trackRepository.findByIsrc(isrc);
    }

    public TrackEntity create(TrackEntity track) {
        if (getByIsrc(track.getIsrc()) != null) throw new ResourceAlreadyExists("The track already exists");
        return trackRepository.save(track);
    }
}
