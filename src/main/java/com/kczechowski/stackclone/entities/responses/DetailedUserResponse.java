package com.kczechowski.stackclone.entities.responses;

import com.kczechowski.stackclone.entities.User;
import lombok.Data;

import java.util.List;

@Data
public class DetailedUserResponse {

    private int id;
    private String nickname;
    private String email;
    private List<String> roles;

}
