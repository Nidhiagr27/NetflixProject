package org.example.service;

import org.example.accessor.S3Accessor;
import org.example.accessor.VideoAccessor;
import org.example.accessor.models.VideoDTO;
import org.example.exceptions.InvalidVideoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VideoService {
    @Autowired
    private VideoAccessor videoAccessor;

    @Autowired
    private S3Accessor s3Accessor;

    public String getVideoUrl(final String videoId){
        VideoDTO videoDTO=videoAccessor.getVideoByVideoId(videoId);
        if(videoDTO==null){
            throw new InvalidVideoException("Video with videoId "+videoId+" doesn't exist!!");
        }
        String videoPath=videoDTO.getVideoPath();

        return s3Accessor.getPreSignedUrl(videoPath, videoDTO.getTotalLength()*60);

    }

    public String getVideoThumbnail(final String videoId){
        VideoDTO videoDTO=videoAccessor.getVideoByVideoId(videoId);
        if(videoDTO==null){
            throw new InvalidVideoException("Video with videoId "+videoId+" doesn't exist!!");
        }
        String thumbnailPath=videoDTO.getThumbnailPath();
        return s3Accessor.getPreSignedUrl(thumbnailPath,2*60);
    }
}
