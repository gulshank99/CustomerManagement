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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        String errorMessage = ex.getMessage();
        // Determine the appropriate response message
        String userFriendlyMessage;
        if (errorMessage != null) {
            userFriendlyMessage =  errorMessage  + "'. Please enter a unique value.";
        } else {
            userFriendlyMessage = "A database constraint was violated. Please ensure unique fields are not duplicated.";
        }

        // Build the error response using ApiResponseMessage
        ApiResponseMessage responseMessage = ApiResponseMessage.builder()
                .message(userFriendlyMessage)
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
    public ResponseEntity<ApiResponseMessage> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        String message = "Invalid input: " + ex.getValue() + ". Expected a numeric value.";
        ApiResponseMessage response = ApiResponseMessage.builder()
                .message(message)
                .success(false)
                .status(HttpStatus.BAD_REQUEST)
                .build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponseMessage> handleIllegalArgumentException(IllegalArgumentException ex) {
        String message = ex.getMessage(); // Get the specific exception message
        ApiResponseMessage response = ApiResponseMessage.builder()
                .message(message) // Use the specific exception message
                .success(false)
                .status(HttpStatus.BAD_REQUEST)
                .build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ApiResponseMessage> handleDuplicateResourceException(DuplicateResourceException ex) {
        ApiResponseMessage responseMessage = ApiResponseMessage.builder()
                .message(ex.getMessage())
                .status(HttpStatus.CONFLICT)
                .success(false)
                .build();

        return new ResponseEntity<>(responseMessage, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(FileNotFoundCustomException.class)
    public ResponseEntity<Object> handleFileNotFoundException(FileNotFoundCustomException ex, WebRequest request) {
        Map<String, Object> body = new HashMap<>();
        body.put("status", HttpStatus.NOT_FOUND.value());
        body.put("error", "File Not Found");
        body.put("message", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

}