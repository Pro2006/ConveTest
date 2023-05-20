package com.example.convetest.model;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandProcessor {
    private static final String API_KEY = "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";
    public static void main(String[] args) {
        String text = "Привет! Выполни команды chatgpt(Пример текста) и chatgpt2(Другой текст)";

        Map<String, CommandHandler> commandHandlers = new HashMap<>();
        commandHandlers.put("chatgpt", new ChatGPTCommandHandler());
        commandHandlers.put("chatgpt2", new ChatGPT2CommandHandler());

        String processedText = processCommands(text, commandHandlers);
        System.out.println(processedText);
    }

    public static String processCommands(String text, Map<String, CommandHandler> commandHandlers) {
        Pattern pattern = Pattern.compile("(\\w+)\\((.*?)\\)");
        Matcher matcher = pattern.matcher(text);

        StringBuffer buffer = new StringBuffer();
        while (matcher.find()) {
            String commandName = matcher.group(1);
            String commandText = matcher.group(2);
            CommandHandler commandHandler = commandHandlers.get(commandName);
            if (commandHandler != null) {
                String processedCommand = commandHandler.processCommand(commandText);
                matcher.appendReplacement(buffer, processedCommand);
            }
        }
        matcher.appendTail(buffer);

        return buffer.toString();
    }

    public interface CommandHandler {
        String processCommand(String commandText);
    }

    public static class ChatGPTCommandHandler implements CommandHandler {
        @Override
        public String processCommand(String commandText) {
            // Обработка команды chatgpt
            return "Processed chatgpt command: " + commandText;
        }
    }

    public static class ChatGPT2CommandHandler implements CommandHandler {
        @Override
        public String processCommand(String commandText) {
            // Обработка команды chatgpt2
            return "Processed chatgpt2 command: " + commandText;
        }
    }
}
