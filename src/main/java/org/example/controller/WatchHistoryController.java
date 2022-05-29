package org.example.controller;

import org.example.controller.models.GetWatchHistoryInput;
import org.example.controller.models.WatchHistoryInput;
import org.example.exceptions.InvalidDataException;
import org.example.exceptions.InvalidProfileException;
import org.example.exceptions.InvalidVideoException;
import org.example.security.Roles;
import org.example.service.WatchHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
public class WatchHistoryController {
    @Autowired
    WatchHistoryService watchHistoryService;

    @PostMapping("/video/{videoId}/watchTime")
    @Secured({Roles.Customer})
    public ResponseEntity<Void> updateWatchHistory(@PathVariable("videoId") String videoId,
                                                   @RequestBody WatchHistoryInput watchHistoryInput){
        try{
            String profileId=watchHistoryInput.getProfileId();
            int watchTime= watchHistoryInput.getWatchTime();
            watchHistoryService.updateWatchHistory(profileId,videoId,watchTime);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        catch (InvalidVideoException | InvalidProfileException | InvalidDataException ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @GetMapping("/video/{videoId}/watchTime")
    @Secured({Roles.Customer})
    public ResponseEntity<Integer> getWatchHistory(@PathVariable("videoId") String videoId,
                                                @RequestBody GetWatchHistoryInput getWatchHistoryInput){
        String profileId= getWatchHistoryInput.getProfileId();
        try{
            int watchLength=watchHistoryService.getWatchHistory(profileId,videoId);
            return ResponseEntity.status(HttpStatus.OK).body(watchLength);
        }
        catch(InvalidVideoException | InvalidProfileException ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
        catch (Exception ex){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    }
}
