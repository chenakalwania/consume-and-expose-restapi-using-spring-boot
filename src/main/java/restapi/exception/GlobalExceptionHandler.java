package restapi.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(FormulaNotFoundException.class)
    public ResponseEntity<Object> appExceptionHandler(FormulaNotFoundException ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(), null, NOT_FOUND, request);
    }

    @ExceptionHandler(ConsumeFormulaAPIException.class)
    public ResponseEntity<Object> appExceptionHandler(ConsumeFormulaAPIException ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(), null, INTERNAL_SERVER_ERROR, request);
    }
}
