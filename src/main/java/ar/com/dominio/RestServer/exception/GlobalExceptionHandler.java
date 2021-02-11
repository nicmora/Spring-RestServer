package ar.com.dominio.RestServer.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    protected ApiError handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        ApiError error = new ApiError();
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setError(HttpStatus.BAD_REQUEST.name());
        error.setMessage("Faltan datos obligatorios");
        error.setTimestamp(LocalDateTime.now());
        List<FieldError> fieldErrors = exception.getFieldErrors();
        if(!fieldErrors.isEmpty()) {
            FieldError fieldError = fieldErrors.get(0);
            error.setFieldError(fieldError.getField());
            error.setFieldErrorMsg(fieldError.getDefaultMessage());
        }
        return error;
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    protected ApiError handleHttpMessageNotReadableException(HttpMessageNotReadableException exception) {
        ApiError error = new ApiError();
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setError(HttpStatus.BAD_REQUEST.name());
        error.setMessage("Formato de los datos incorrectamente");
        error.setTimestamp(LocalDateTime.now());
        //        List<FieldError> fieldErrors = exception..getFieldErrors();
//        if(!fieldErrors.isEmpty()) {
//            FieldError fieldError = fieldErrors.get(0);
//            error.setFieldError(fieldError.getField());
//            error.setFieldErrorMsg(fieldError.getDefaultMessage());
//        }
        return error;
    }

    @ExceptionHandler(ResponseStatusException.class)
    protected ResponseStatusException handleResponseStatusException(ResponseStatusException exception) {
        throw exception;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    protected ApiError handleException(Exception exception, HttpServletRequest request) {
        ApiError error = new ApiError();
        error.setError(HttpStatus.INTERNAL_SERVER_ERROR.name());
        error.setMessage("Error interno del servidor");
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
//        error.setPath(request.getServletPath());
        error.setTimestamp(LocalDateTime.now());
        return error;
    }

}
