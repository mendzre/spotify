package com.codechallenge.spotify.repositories;

import com.codechallenge.spotify.models.TrackMetadataEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackMetadataRepository extends CrudRepository<TrackMetadataEntity, Long> {
}
