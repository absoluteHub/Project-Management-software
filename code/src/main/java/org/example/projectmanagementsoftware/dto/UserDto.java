package org.example.projectmanagementsoftware.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private Long id;
    private String fullName;
    private String role;
    private String email;
}
