package server.example.assignment.file_limit.exception;

import org.springframework.http.HttpStatus;

public enum FileLimitExceptionType {
    ALREADY_EXIST_NAME(600, HttpStatus.CONFLICT, "이미 존재하는 확장자입니다."),
    NAME_OVERLOAD(601, HttpStatus.BAD_REQUEST, "확장자 이름이 20자 초과되었습니다."),
    LIMIT_OVERLOAD(602, HttpStatus.BAD_REQUEST, "확장자가 너무 많습니다.");

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
