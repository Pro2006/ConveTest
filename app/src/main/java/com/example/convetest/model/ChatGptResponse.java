package com.example.convetest.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ChatGptResponse {
    private String id;
    private String object;
    private int created;
    private String model;
    private ResponseChoice[] choices;
    private ResponseUsage[] usages;
}
