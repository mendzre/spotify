package com.codechallenge.spotify.controllers;

import com.codechallenge.spotify.mappers.TrackMapper;
import com.codechallenge.spotify.mappers.TrackMetadataMapper;
import com.codechallenge.spotify.models.TrackEntity;
import com.codechallenge.spotify.models.TrackMetadataEntity;
import com.codechallenge.spotify.models.TrackResponse;
import com.codechallenge.spotify.services.SpotifyApiService;
import com.codechallenge.spotify.services.TrackMetadataService;
import com.codechallenge.spotify.services.TrackService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@Tag(name = "Track")
public class TrackController {
    private final SpotifyApiService spotifyApiService;
    private final TrackService trackService;
    private final TrackMetadataService trackMetadataService;

    @Autowired
    public TrackController(TrackService trackService, SpotifyApiService spotifyApiService, TrackMetadataService trackMetadataService) {
        this.trackService = trackService;
        this.spotifyApiService = spotifyApiService;
        this.trackMetadataService = trackMetadataService;
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created",
                    content = @Content(schema = @Schema(implementation = TrackEntity.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
            @ApiResponse(responseCode = "409", description = "Conflict", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    @PostMapping("createTrack")
    public ResponseEntity<TrackEntity> create(@RequestParam(name = "isrc") String isrc) throws JsonProcessingException {
        TrackResponse raw = spotifyApiService.getTrackByIsrc(isrc);

        TrackMetadataEntity mappedTrackMetadata = TrackMetadataMapper.toEntity(raw);
        TrackMetadataEntity trackMetadata = trackMetadataService.create(mappedTrackMetadata);
        TrackEntity mappedTrack = TrackMapper.toEntity(raw);
        mappedTrack.setMetadata(trackMetadata);
        return new ResponseEntity<>(trackService.create(mappedTrack), HttpStatus.CREATED);
    }
}
