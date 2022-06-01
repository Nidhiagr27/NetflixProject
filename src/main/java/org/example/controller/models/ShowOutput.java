package org.example.controller.models;

import lombok.Builder;
import lombok.Getter;
import org.example.accessor.models.Genre;
import org.example.accessor.models.ProfileType;
import org.example.accessor.models.ShowType;

@Builder
@Getter
public class ShowOutput {
    private String showId;
    private String name;
    private ShowType showType;
    private Genre genre;
    private ProfileType audience;
    private double rating;
    private int length;
    private String thumbnailPath;
}
