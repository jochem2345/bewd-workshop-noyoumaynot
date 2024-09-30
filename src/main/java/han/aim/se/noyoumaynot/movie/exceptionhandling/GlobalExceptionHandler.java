package han.aim.se.noyoumaynot.movie.exceptionhandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.naming.AuthenticationException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<String> handleAuthenticationException(AuthenticationException ae) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ae.getMessage());
    }
}
