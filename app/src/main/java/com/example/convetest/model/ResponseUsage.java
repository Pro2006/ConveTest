package com.example.convetest.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseUsage {
    private int prompt_tokens;
    private int completion_tokens;
    private int total_tokens;
}
