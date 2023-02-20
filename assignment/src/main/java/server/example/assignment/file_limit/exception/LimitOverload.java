package server.example.assignment.file_limit.exception;

import lombok.Getter;

@Getter
public class LimitOverload extends FileLimitException{

    private static final String MESSAGE = "커스텀 확장자는 200개가 넘을 수 없습니다.";

    public String fieldName;
    public String message;

    public LimitOverload() {
        super(MESSAGE);
    }

    public LimitOverload(String fieldName, String message) {
        super(MESSAGE);
        addValidation(fieldName, message);
    }

    @Override
    public int getStatusCode() {
        return 400;
    }
}
