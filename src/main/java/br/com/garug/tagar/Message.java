package br.com.garug.tagar;

import java.time.LocalDateTime;

public class Message {
    private String from;
    private String message;

    public Message() {
        // required for Jackson
    }

    public Message(String from, String message, LocalDateTime timestamp) {
        this.from = from;
        this.message = message;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
