public class LimitException extends RuntimeException {
    int attempts;

    public LimitException(){}

    public LimitException(int attempts, final String message){
        super(message);
        this.attempts = attempts;
    }
}