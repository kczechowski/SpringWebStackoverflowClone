package com.kczechowski.stackclone.entities.requests;

import lombok.Data;

@Data
public class RegisterUserRequest {

    private String nickname;
    private String password;
    private String email;

}

