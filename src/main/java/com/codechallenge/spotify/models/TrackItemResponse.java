package com.codechallenge.spotify.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class TrackItemResponse {
    private AlbumResponse album;
    private List<ArtistResponse> artists;
    @JsonProperty("available_markets")
    private List<String> availableMarkets;
    @JsonProperty("disc_number")
    private long discNumber;
    @JsonProperty("duration_ms")
    private long durationMS;
    private boolean explicit;
    @JsonProperty("external_ids")
    private ExternalIDSResponse externalIDS;
    @JsonProperty("external_urls")
    private ExternalUrlsResponse externalUrls;
    private String href;
    private String id;
    @JsonProperty("is_local")
    private boolean isLocal;
    private String name;
    private long popularity;
    @JsonProperty("preview_url")
    private Object previewURL;
    @JsonProperty("track_number")
    private long trackNumber;
    private String type;
    private String uri;
}
