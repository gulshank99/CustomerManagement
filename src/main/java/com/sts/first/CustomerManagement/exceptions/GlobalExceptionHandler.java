package com.sts.first.CustomerManagement.exceptions;

import com.sts.first.CustomerManagement.dtos.ApiResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    @ExceptionHandler
    public ResponseEntity<ApiResponseMessage> resourceNotFoundExceptionHandler(ResourceNotFoundException ex){
        logger.info("Exception Handler Invoked");

        ApiResponseMessage responseMessage = ApiResponseMessage.builder().message(ex.getMessage()).status(HttpStatus.NOT_FOUND).success(true).build();
        return new ResponseEntity<>(responseMessage,HttpStatus.NOT_FOUND);
    }

    //Method ArgumentNotValidException
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,Object>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        logger.info("Exception Handler Invoked");

        List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();
        Map<String,Object> response =new HashMap<>();
        allErrors.stream().forEach(objectError -> {
            String message = objectError.getDefaultMessage();
            String field= ((FieldError)objectError).getField();
            response.put(field,message);
        });

        return  new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    //Bad api Exception
    @ExceptionHandler(BadApiRequestException.class)
    public ResponseEntity<ApiResponseMessage> handleBadApiRequest(BadApiRequestException ex){
        logger.info("Bad Api Request", ex);

        ApiResponseMessage responseMessage = ApiResponseMessage.builder().message(ex.getMessage()).status(HttpStatus.BAD_REQUEST).success(false).build();
        return new ResponseEntity<>(responseMessage,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<ApiResponseMessage> handleSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException ex) {
        // Log the exception
        logger.error("Database constraint violation: ", ex);

        // Build an error response
        ApiResponseMessage responseMessage = ApiResponseMessage.builder()
                .message("A database constraint was violated. Please ensure unique fields are not duplicated.")
                .status(HttpStatus.BAD_REQUEST)
                .success(false)
                .build();

        return new ResponseEntity<>(responseMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleJsonParseException(HttpMessageNotReadableException ex, WebRequest request) {
        // Customize the error message based on the exception details
        String errorMessage = "Invalid JSON input: " + ex.getMostSpecificCause().getMessage();

        return ResponseEntity.badRequest().body(errorMessage);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        String requiredType = ex.getRequiredType() != null ? ex.getRequiredType().getSimpleName() : "unknown";

        // Custom error message based on the type
        String errorMessage;
        switch (requiredType) {
            case "Boolean":
                errorMessage = String.format(
                        "Invalid boolean value '%s' for parameter '%s'. Expected 'true' or 'false'.",
                        ex.getValue(),
                        ex.getName()
                );
                break;
            case "Long":
                errorMessage = String.format(
                        "Invalid long value '%s' for parameter '%s'. Please provide a valid integer value.",
                        ex.getValue(),
                        ex.getName()
                );
                break;
            case "Double":
                errorMessage = String.format(
                        "Invalid double value '%s' for parameter '%s'. Please provide a valid decimal number.",
                        ex.getValue(),
                        ex.getName()
                );
                break;
            case "Integer":
                errorMessage = String.format(
                        "Invalid integer value '%s' for parameter '%s'. Please provide a valid integer.",
                        ex.getValue(),
                        ex.getName()
                );
                break;
            case "String":
                errorMessage = String.format(
                        "Invalid string value for parameter '%s'. Please provide a valid text value.",
                        ex.getName()
                );
                break;
            default:
                errorMessage = String.format(
                        "Invalid value '%s' for parameter '%s'. Expected a value of type '%s'.",
                        ex.getValue(),
                        ex.getName(),
                        requiredType
                );
                break;
        }

        return ResponseEntity.badRequest().body(errorMessage);
    }

}