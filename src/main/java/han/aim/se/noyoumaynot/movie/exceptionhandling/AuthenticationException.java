package han.aim.se.noyoumaynot.movie.exceptionhandling;

public class AuthenticationException extends RuntimeException {
    public AuthenticationException(String message) {
        super(message);
    }
}
