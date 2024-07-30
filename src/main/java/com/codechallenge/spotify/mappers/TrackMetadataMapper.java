package com.codechallenge.spotify.mappers;

import com.codechallenge.spotify.models.*;

public class TrackMetadataMapper {

    public static TrackMetadataEntity toEntity(TrackResponse response) {
        if (response == null) return null;
        TrackItemResponse item = response.getTracks().getItems().get(0);
        if (item == null) return null;
        ArtistResponse artist = item.getArtists().get(0);
        if (artist == null) return null;
        AlbumResponse album = item.getAlbum();
        if (album == null) return null;

        TrackMetadataEntity trackMetadata = new TrackMetadataEntity();
        trackMetadata.setName(item.getName());
        trackMetadata.setArtistName(artist.getName());
        trackMetadata.setAlbumName(album.getName());
        trackMetadata.setAlbumId(album.getId());
        trackMetadata.setIsExplicit(item.isExplicit());
        long computedSeconds = item.getDurationMS() / 100;
        trackMetadata.setPlaybackSeconds(computedSeconds);
        return trackMetadata;
    }
}
