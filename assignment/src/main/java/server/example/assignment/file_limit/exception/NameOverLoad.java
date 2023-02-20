package server.example.assignment.file_limit.exception;

import lombok.Getter;

@Getter
public class NameOverLoad extends FileLimitException{

    private static final String MESSAGE = "확장자 이름은 20자가 최대입니다.";

    public String fieldName;
    public String message;

    public NameOverLoad() {
        super(MESSAGE);
    }

    public NameOverLoad(String fieldName, String message) {
        super(MESSAGE);
        addValidation(fieldName, message);
    }

    @Override
    public int getStatusCode() {
        return 400;
    }
}
