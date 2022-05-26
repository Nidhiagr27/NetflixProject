package org.example.controller.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateUserInput {
    private String name;
    private String email;
    private String phone;
    private String password;
}