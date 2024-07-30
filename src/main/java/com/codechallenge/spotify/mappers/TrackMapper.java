package com.codechallenge.spotify.mappers;

import com.codechallenge.spotify.models.TrackEntity;
import com.codechallenge.spotify.models.TrackItemResponse;
import com.codechallenge.spotify.models.TrackResponse;

public class TrackMapper {

    public static TrackEntity toEntity(TrackResponse response) {
        TrackItemResponse body = response.getTracks().getItems().get(0);
        if (body == null) return null;
        TrackEntity track = new TrackEntity();
        track.setIsrc(body.getExternalIDS().getIsrc());
        return track;
    }
}
