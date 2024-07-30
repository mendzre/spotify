package com.codechallenge.spotify.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ArtistResponse {
    @JsonProperty("external_urls")
    private ExternalUrlsResponse externalUrls;
    private String href;
    private String id;
    private String name;
    private String type;
    private String uri;
}
