package org.example.accessor.models;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ShowDTO {
    private String showId;
    private String name;
    private ShowType showType;
    private Genre genre;
    private ProfileType audience;
    private double rating;
    private int length;
    private String thumbnailPath;
}
