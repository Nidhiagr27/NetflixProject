package org.example.validator;

import org.example.accessor.ProfileAccessor;
import org.example.accessor.models.ProfileDTO;
import org.example.exceptions.InvalidProfileException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Validator {

    @Autowired
    private ProfileAccessor profileAccessor;

    public void validateProfile(final String profileId,final String userId){
        ProfileDTO profileDTO=profileAccessor.getProfileByProfileId(profileId);
        if(profileDTO == null && !profileDTO.getUserId().equals(userId)){
            throw  new InvalidProfileException("Profile "+profileId+" is invalid or doesn't exist!");
        }
    }
}
