package server.example.assignment.file_limit.exception;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public abstract class FileLimitException extends RuntimeException{

    public final Map<String, String> validation = new HashMap<>();

    public FileLimitException(String message) {
        super(message);
    }

    public FileLimitException(String message, Throwable cause) {
        super(message, cause);
    }

    public abstract int getStatusCode();

    public void addValidation(String fieldName, String message) {
        validation.put(fieldName, message);
    }

}
