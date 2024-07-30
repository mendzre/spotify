package com.codechallenge.spotify.controllers;

import com.codechallenge.spotify.exceptions.ResourceNotFoundException;
import com.codechallenge.spotify.models.TrackEntity;
import com.codechallenge.spotify.models.TrackMetadataEntity;
import com.codechallenge.spotify.services.TrackService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@Tag(name = "Track Metadata")
public class TrackMetadataController {
    @Autowired
    private TrackService trackService;

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(schema = @Schema(implementation = TrackMetadataEntity.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)
    })
    @GetMapping("getTrackMetadata")
    public ResponseEntity<TrackMetadataEntity> get(@RequestParam(name = "isrc") String isrc) {
        TrackEntity foundTrack = trackService.getByIsrc(isrc);
        if (foundTrack == null) throw new ResourceNotFoundException("Track Metadata not found with isrc: " + isrc);
        System.out.println(foundTrack.getMetadata());
        return new ResponseEntity<>(foundTrack.getMetadata(), HttpStatus.OK);
    }
}
