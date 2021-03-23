package au.com.wesfarmers.exception;

public class InvalidFileNameException extends Throwable {
    private String message="";
    public InvalidFileNameException(String message) {
        this.message = message;
    }
    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
