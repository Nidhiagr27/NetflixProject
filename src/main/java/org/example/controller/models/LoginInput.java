package org.example.controller.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginInput {
    private String email;
    private String password;
}
