package org.example.accessor.models;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserDTO {
    private String userId;
    private String name;
    private String email;
    private String password;
    private String phone;
    private UserState state;
    private UserRole role;
}