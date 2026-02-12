package dev.antoniogrillo.primoesempiocineca.exception;

import org.springframework.http.HttpStatus;

public class CustomResponseException extends RuntimeException {
    private HttpStatus status;
    public CustomResponseException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }
    public HttpStatus getStatus() {
        return status;
    }
}
