package org.example.service;

import org.example.accessor.ProfileAccessor;
import org.example.accessor.models.ProfileType;
import org.example.accessor.models.UserDTO;
import org.example.controller.models.ProfileTypeInput;
import org.example.exceptions.InvalidDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class ProfileService {

    @Autowired
    ProfileAccessor profileAccessor;

    public void activateProfile(final String name, final ProfileTypeInput profileType){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        UserDTO userDTO=(UserDTO)authentication.getPrincipal();

           if(name.length()<5 || name.length()>20){
               throw new InvalidDataException("Name lentgh should be 5 and 20");
           }
           profileAccessor.addNewProfile(userDTO.getUserId(),name,ProfileType.valueOf(profileType.name()));
    }
}
