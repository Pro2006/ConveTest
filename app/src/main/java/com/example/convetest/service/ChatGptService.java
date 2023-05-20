package com.example.convetest.service;

import com.google.gson.Gson;
import com.example.convetest.model.ChatGptRequest;
import com.example.convetest.model.ChatGptResponse;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.RequestBody;

public class ChatGptService {
    public static final String API_URL = "https://api.openai.com/v1/completions";
    private final Gson gson = new Gson();
    private final String apiKey;
    private final OkHttpClient client;

    public ChatGptService(String apiKey) {
        this.apiKey = apiKey;
        this.client = new OkHttpClient();
    }

    public String search(String searchString) throws IOException {
        ChatGptRequest chatGptRequest = new ChatGptRequest(
                "text-davinci-003",
                searchString,
                1,
                100
        );

        String body = gson.toJson(chatGptRequest);

        Request request = new Request.Builder()
                .url(API_URL)
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + apiKey)
                .post(RequestBody.create(MediaType.parse("application/json"), body))
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                ChatGptResponse chatGptResponse = gson.fromJson(response.body().string(), ChatGptResponse.class);
                String answer = chatGptResponse.getChoices()[0].getText();

                if (!answer.isEmpty()) {
                    return answer.replace("\n", "").trim();
                } else {
                    return "no answers found";
                }
            } else {
                return String.valueOf(response.code());
            }
        }
    }
}