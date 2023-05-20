package com.example.convetest.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.convetest.R;
import com.example.convetest.service.ChatGptService;

import java.io.IOException;
import java.util.Scanner;
import com.example.convetest.service.ChatGptService;


public class MainActivity extends AppCompatActivity {

    // создание полей
    private ImageButton imageButton;
    private static final String API_KEY = "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";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // присваивание id полям
        imageButton = findViewById(R.id.imageButton);

        // обработка нажатия кнопки
        imageButton.setOnClickListener(listener);

        ChatGptService service = new ChatGptService(API_KEY);

        System.out.println("Java CLI application that use Chat GPT API");
        System.out.println("---");

        String queryString = "";
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println();
            System.out.println("Enter a string to search for (or /q to quit): ");
            queryString = scanner.nextLine();

            if (queryString.equals("/q")) {
                break;
            }

            System.out.println("Searching...");
            String answer = null;
            try {
                answer = service.search(queryString);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println(answer);
        }
    }


    // создание слушателя
    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            // переключение на новую активность
            Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
            startActivity(intent);

        }
    };
}