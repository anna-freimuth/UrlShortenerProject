package anna.freimuth.urlshortener.controller;

import anna.freimuth.urlshortener.exception.EntityNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {EntityNotFoundException.class})
    protected ResponseEntity<Object> handleNotFound(
        EntityNotFoundException exception, WebRequest request) {
        return handleExceptionInternal(exception, exception.getMessage(),
            new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
}
