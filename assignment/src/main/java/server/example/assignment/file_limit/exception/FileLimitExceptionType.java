package server.example.assignment.file_limit.exception;

import org.springframework.http.HttpStatus;

public enum FileLimitExceptionType {
    ALREADY_EXIST_NAME(600, HttpStatus.CONFLICT, "이미 존재하는 확장자입니다.");

    private int errorCode;
    private HttpStatus httpStatus;
    private String errorMessage;

    FileLimitExceptionType(int errorCode, HttpStatus httpStatus, String errorMessage) {
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
        this.errorMessage = errorMessage;
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }
}
