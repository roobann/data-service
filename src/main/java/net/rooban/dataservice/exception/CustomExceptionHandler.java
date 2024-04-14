package net.rooban.dataservice.exception;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(value = {ConstraintViolationException.class})
    public ResponseEntity<ValidationResponse> handleException(ConstraintViolationException ex) {
        List<String> errorMessage = new ArrayList<>();
        for(ConstraintViolation cv : ex.getConstraintViolations()) {
            errorMessage.add(cv.getMessage());
        }
        return new ResponseEntity<>(new ValidationResponse(errorMessage.toArray(new String[0]), HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {CustomException.class})
    public ResponseEntity<ErrorResponse> handleCustomException(CustomException ex) {
        return new ResponseEntity<>(new ErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
    }

    @Getter
    @RequiredArgsConstructor
    static class ValidationResponse {
        private final String[] errorMessage;
        private final Integer code;
    }

    @Getter
    @RequiredArgsConstructor
    static class  ErrorResponse{
        private final String errorMessage;
        private final Integer code;
    }


}
