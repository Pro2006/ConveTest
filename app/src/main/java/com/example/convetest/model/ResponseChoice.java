package com.example.convetest.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseChoice {
    private String text;
    private int index;
    private Object logprobs;
    private String finish_reason;
}
