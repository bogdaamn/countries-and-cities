package com.cities.exception.handler;

import java.time.LocalDateTime;
import java.util.List;

public class ErrorResponseDto {


    private final LocalDateTime timestamp;
    private final int status;
    private final String error;
    private final List<String> messages;
    private final String path;

    public ErrorResponseDto(LocalDateTime timestamp,
                            int status,
                            String error,
                            String message,
                            String path) {
        this(timestamp, status, error, List.of(message), path);
    }

    public ErrorResponseDto(LocalDateTime timestamp, int status, String error, List<String> messages, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.messages = messages;
        this.path = path;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public int getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public List<String> getMessages() {
        return messages;
    }

    public String getPath() {
        return path;
    }
}
