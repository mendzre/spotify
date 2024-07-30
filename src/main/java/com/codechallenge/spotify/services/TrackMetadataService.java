package com.codechallenge.spotify.services;

import com.codechallenge.spotify.models.TrackMetadataEntity;
import com.codechallenge.spotify.repositories.TrackMetadataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrackMetadataService {
    @Autowired
    TrackMetadataRepository trackMetadataRepository;

    public TrackMetadataEntity create(TrackMetadataEntity trackMetadata) {
        return trackMetadataRepository.save(trackMetadata);
    }
}
