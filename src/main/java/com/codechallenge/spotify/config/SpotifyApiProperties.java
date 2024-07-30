package com.codechallenge.spotify.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@Configuration
@ConfigurationProperties("spotify-api")
public class SpotifyApiProperties {
    private String url;
    @Value("access-token")
    private String accessToken;
}
