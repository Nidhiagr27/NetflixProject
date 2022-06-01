package org.example.mapper;

import org.example.accessor.S3Accessor;
import org.example.accessor.models.Genre;
import org.example.accessor.models.ShowDTO;
import org.example.controller.models.ShowOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShowMapper {

   @Autowired
    private S3Accessor s3Accessor;

    public ShowOutput mapShowDtoToOutput(final ShowDTO input){
        ShowOutput output=ShowOutput.builder()
                .showId(input.getShowId())
                .name(input.getName())
                .showType(input.getShowType())
                .genre(input.getGenre())
                .audience(input.getAudience())
                .rating(input.getRating())
                .length(input.getLength())
                .thumbnailPath(s3Accessor.getPreSignedUrl(input.getThumbnailPath(),5*60))
                .build();
        return output;

    }
}
