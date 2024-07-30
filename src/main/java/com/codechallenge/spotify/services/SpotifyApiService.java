package com.codechallenge.spotify.services;

import com.codechallenge.spotify.config.SpotifyApiProperties;
import com.codechallenge.spotify.exceptions.AccessTokenNotSetException;
import com.codechallenge.spotify.exceptions.ResourceNotFoundException;
import com.codechallenge.spotify.models.TrackResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SpotifyApiService {
    private final SpotifyApiProperties spotifyApiProperties;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Autowired
    public SpotifyApiService(SpotifyApiProperties spotifyApiProperties, RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.spotifyApiProperties = spotifyApiProperties;
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public TrackResponse getTrackByIsrc(String isrc) throws JsonProcessingException {
        String accessToken = spotifyApiProperties.getAccessToken();
        if (accessToken == null || accessToken.isEmpty())
            throw new AccessTokenNotSetException("Failed to authenticate with the external service");
        int limit = 1;
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);

        ResponseEntity<String> response = restTemplate.exchange(
                String.format("%s/search?type=track&q=isrc:%s&limit=%d", spotifyApiProperties.getUrl(), isrc, limit),
                HttpMethod.GET,
                new HttpEntity<>(headers), String.class
        );
        TrackResponse mappedTrack = objectMapper.readValue(response.getBody(), TrackResponse.class);
        if (mappedTrack.getTracks().getItems().isEmpty())
            throw new ResourceNotFoundException("Track not found with isrc: " + isrc);
        return mappedTrack;
    }
}
