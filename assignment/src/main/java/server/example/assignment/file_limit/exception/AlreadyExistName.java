package server.example.assignment.file_limit.exception;

import lombok.Getter;

@Getter
public class AlreadyExistName extends FileLimitException{

    private static final String MESSAGE = "이미 제한된 확장자입니다.";

    public String fieldName;
    public String message;

    public AlreadyExistName() {
        super(MESSAGE);
    }

    public AlreadyExistName(String fieldName, String message) {
        super(MESSAGE);
        addValidation(fieldName, message);
    }

    @Override
    public int getStatusCode() {
        return 400;
    }
}
