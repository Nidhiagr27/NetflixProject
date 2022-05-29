package org.example.validator;

import org.example.accessor.ProfileAccessor;
import org.example.accessor.VideoAccessor;
import org.example.accessor.models.ProfileDTO;
import org.example.accessor.models.VideoDTO;
import org.example.exceptions.InvalidProfileException;
import org.example.exceptions.InvalidVideoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Validator {

    @Autowired
    private ProfileAccessor profileAccessor;

    @Autowired
    private VideoAccessor videoAccessor;

    public void validateProfile(final String profileId,final String userId){
        ProfileDTO profileDTO=profileAccessor.getProfileByProfileId(profileId);
        System.out.println("profileId = "+profileDTO.getProfileId());
        System.out.println(profileId);
        if(profileDTO == null || !profileDTO.getUserId().equals(userId)){
            System.out.println(profileId);
            throw  new InvalidProfileException("Profile "+profileId+" is invalid or doesn't exist!");
        }
    }

    public void validateVideoId(final String videoId){
        VideoDTO videoDTO= videoAccessor.getVideoByVideoId(videoId);
        if(videoDTO==null){
            throw new InvalidVideoException("Video with videoId "+videoId+" is invalid or do not exist!!");
        }
    }
}
