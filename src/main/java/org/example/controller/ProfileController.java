package org.example.controller;

import org.example.controller.models.CreateProfileInput;
import org.example.security.Roles;
import org.example.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProfileController {
    @Autowired
    ProfileService profileService;

    @PostMapping("/user/profile")
    @Secured({Roles.Customer})
    public ResponseEntity<Void> activateProfile(@RequestBody CreateProfileInput createProfileInput){
        try{
        profileService.activateProfile(createProfileInput.getName(), createProfileInput.getProfileType());
        return ResponseEntity.status(HttpStatus.OK).build();
        }
        catch(Exception ex){
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }
}
