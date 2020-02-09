package com.kczechowski.stackclone.entities.requests;

import lombok.Data;

@Data
public class AddQuestionRequest {
    private String title;
    private String content;
    private int[] tags;
}
