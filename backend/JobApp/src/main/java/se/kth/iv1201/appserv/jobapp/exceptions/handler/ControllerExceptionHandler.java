package se.kth.iv1201.appserv.jobapp.exceptions.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import se.kth.iv1201.appserv.jobapp.exceptions.ErrorMessage;
import se.kth.iv1201.appserv.jobapp.exceptions.IllegalJobApplicationUpdateException;
import se.kth.iv1201.appserv.jobapp.exceptions.IllegalUserAuthenticationException;
import se.kth.iv1201.appserv.jobapp.exceptions.IllegalUserRegisterException;
import se.kth.iv1201.appserv.jobapp.exceptions.InternalDataBaseErrorException;

import java.net.ConnectException;
import java.util.Date;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(value = {IllegalUserAuthenticationException.class} )
    public ResponseEntity <ErrorMessage> handleUserException(IllegalUserAuthenticationException e, WebRequest request){
        ErrorMessage message = new ErrorMessage(
                HttpStatus.UNAUTHORIZED.value(),
                new Date(),
                e.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(message, HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler(value = {IllegalJobApplicationUpdateException.class} )
    public ResponseEntity <ErrorMessage> handleApplicationException(IllegalJobApplicationUpdateException e, WebRequest request){
        ErrorMessage message = new ErrorMessage(
                HttpStatus.PRECONDITION_FAILED.value(),
                new Date(),
                e.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(message, HttpStatus.PRECONDITION_FAILED);
    }

    @ExceptionHandler(value = {IllegalUserRegisterException.class} )
    public ResponseEntity <ErrorMessage> handleRegisterException(IllegalUserRegisterException e, WebRequest request){
        ErrorMessage message = new ErrorMessage(
                HttpStatus.CONFLICT.value(),
                new Date(),
                e.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(message, HttpStatus.CONFLICT);
    }

    @ControllerAdvice
    @ResponseBody
    public class DatabaseExceptionHandler {
        @ExceptionHandler(value = {CannotCreateTransactionException.class})
        public ResponseEntity<?> cannotCreateTransactionException(CannotCreateTransactionException exception, WebRequest request) {
            if (exception.contains(ConnectException.class)) {
                return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
            }else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }
    }
}
