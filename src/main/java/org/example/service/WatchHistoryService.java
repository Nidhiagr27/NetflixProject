package org.example.service;


import org.example.accessor.VideoAccessor;
import org.example.accessor.WatchHistoryAccessor;
import org.example.accessor.models.UserDTO;
import org.example.accessor.models.VideoDTO;
import org.example.accessor.models.WatchHistoryDTO;
import org.example.exceptions.InvalidDataException;
import org.example.exceptions.InvalidVideoException;
import org.example.validator.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class WatchHistoryService {

    @Autowired
    private Validator validator;

    @Autowired
    private WatchHistoryAccessor watchHistoryAccessor;

    @Autowired
    private VideoAccessor videoAccessor;

    public void updateWatchHistory(final String profileId, final String videoId,final int watchedLength){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        UserDTO userDTO=(UserDTO)authentication.getPrincipal();
        validator.validateProfile(profileId, userDTO.getUserId());
        VideoDTO videoDTO= videoAccessor.getVideoByVideoId(videoId);
        if(videoDTO==null){
            throw new InvalidVideoException("Video with videoId "+videoId+" is invalid or do not exist!!");
        }
        if(watchedLength <1 || watchedLength>videoDTO.getTotalLength()){
            throw new InvalidDataException("Watched length is invalid!");
        }
        WatchHistoryDTO watchHistoryDTO=watchHistoryAccessor.getWatchHistory(profileId,videoId);
        // Inserting for the first time
        if(watchHistoryDTO==null){
           watchHistoryAccessor.insertWatchHistory(profileId,videoId,0.0,watchedLength);
        }
        else{
            watchHistoryAccessor.updateWatchedLength(profileId, videoId, watchedLength);
        }

    }


}
