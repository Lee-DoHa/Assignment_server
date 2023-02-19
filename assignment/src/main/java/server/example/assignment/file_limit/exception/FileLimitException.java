package server.example.assignment.file_limit.exception;

public class FileLimitException extends RuntimeException{


    private final FileLimitExceptionType exceptionType;

    public FileLimitException(FileLimitExceptionType exceptionType) {
        this.exceptionType = exceptionType;
    }

}
