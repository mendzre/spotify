package com.codechallenge.spotify.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class AlbumResponse {
    @JsonProperty("album_type")
    private String albumType;
    private List<ArtistResponse> artists;
    @JsonProperty("available_markets")
    private List<String> availableMarkets;
    @JsonProperty("external_urls")
    private ExternalUrlsResponse externalUrls;
    private String href;
    private String id;
    private List<ImageResponse> images;
    private String name;
    @JsonProperty("release_date")
    private LocalDate releaseDate;
    @JsonProperty("release_date_precision")
    private String releaseDatePrecision;
    @JsonProperty("total_tracks")
    private long totalTracks;
    private String type;
    private String uri;
}
