package com.codechallenge.spotify.repositories;

import com.codechallenge.spotify.models.TrackEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackRepository extends CrudRepository<TrackEntity, Long> {
    TrackEntity findByIsrc(String isrc);
}
