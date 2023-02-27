package se.kth.iv1201.appserv.jobapp.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class WebExceptionHandler {
    @ExceptionHandler(value = {ApplicationException.class} )
    public ResponseEntity <Object> handleException(){
        HttpStatus conflict = HttpStatus.CONFLICT;
        ApplicationException applicationException = new ApplicationException();
        return new ResponseEntity<>(applicationException, conflict);

    }
}
