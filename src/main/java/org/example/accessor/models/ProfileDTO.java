package org.example.accessor.models;

import lombok.Builder;
import lombok.Getter;

import java.sql.Date;

@Getter
@Builder
public class ProfileDTO {
     private String profileId;
     private String name;
     private ProfileType profileType;
     private Date createdAt;
     private String userId;
}
