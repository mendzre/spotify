package com.codechallenge.spotify.models;


import lombok.Data;

@Data
public class TrackResponse {
    private SpotifyServiceResponse<TrackItemResponse> tracks;
}
