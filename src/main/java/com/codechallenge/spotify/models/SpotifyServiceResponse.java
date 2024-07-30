package com.codechallenge.spotify.models;

import lombok.Data;

import java.util.List;

@Data
public class SpotifyServiceResponse<T> {
    private String href;
    private List<T> items;
    private long limit;
    private String next;
    private long offset;
    private String previous;
    private long total;
}
