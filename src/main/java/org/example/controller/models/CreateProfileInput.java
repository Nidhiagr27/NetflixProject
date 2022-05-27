package org.example.controller.models;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateProfileInput {
    private String name;
    private ProfileTypeInput profileType;
}
