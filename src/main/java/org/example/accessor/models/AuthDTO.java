package org.example.accessor.models;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AuthDTO {
    private String authId;
    private String token;
    private String userId;
}