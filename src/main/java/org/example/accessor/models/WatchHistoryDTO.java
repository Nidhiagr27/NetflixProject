package org.example.accessor.models;

import lombok.Builder;
import lombok.Getter;

import java.sql.Date;

@Builder
@Getter
public class WatchHistoryDTO {
    private String profileId;
    private String videoId;
    private double rating;
    private Date lastWacthedAt;
    private Date firstWatchedAt;
    private int watchedLength;
}
