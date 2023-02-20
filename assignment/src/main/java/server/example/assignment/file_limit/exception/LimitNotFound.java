package server.example.assignment.file_limit.exception;

public class LimitNotFound extends FileLimitException{

    private static final String MESSAGE = "존재하지 않는 확장자입니다.";

    public LimitNotFound() {
        super(MESSAGE);
    }


    @Override
    public int getStatusCode() {
        return 404;
    }
}
