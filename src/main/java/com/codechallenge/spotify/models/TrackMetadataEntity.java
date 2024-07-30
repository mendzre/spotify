package com.codechallenge.spotify.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(exclude = "track")
@Entity
@Table(name = "track_metadata")
public class TrackMetadataEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @NotBlank
    @Column(name = "artist_name", nullable = false)
    private String artistName;

    @NotBlank
    @Column(name = "album_name", nullable = false)
    private String albumName;

    @NotBlank
    @Column(name = "album_id", nullable = false)
    private String albumId;

    @NotNull
    @Column(name = "is_explicit", nullable = false)
    private Boolean isExplicit;

    @NotNull
    @Column(name = "playback_seconds", nullable = false)
    private Long playbackSeconds;

    @JsonBackReference
    @OneToOne(mappedBy = "metadata")
    @Schema(hidden = true)
    private TrackEntity track;
}
