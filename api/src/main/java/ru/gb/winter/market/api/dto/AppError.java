package ru.gb.winter.market.api.dto;

public class AppError {
    private int statusCode;
    private String statusStrCode;
    private String message;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatusStrCode() {
        return statusStrCode;
    }

    public void setStatusStrCode(String statusStrCode) {
        this.statusStrCode = statusStrCode;
    }

    public AppError(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public AppError(String statusStrCode, String message) {
        this.statusStrCode = statusStrCode;
        this.message = message;
    }
}