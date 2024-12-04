package com.bookstore.api.bookstore.controlleradvice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.bookstore.api.bookstore.custom.InvalidException;
import com.bookstore.api.bookstore.dto.ErrorDepscriptionDetail;
import com.bookstore.api.bookstore.model.ApiResponse;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<Object> handleMethodInvalidException(
            ArithmeticException ex, WebRequest request) {
        ErrorDepscriptionDetail error = new ErrorDepscriptionDetail();
        error.setError(ex.getMessage());
        List<String> details = new ArrayList<>();
        details.add("Error by zero");
        error.setDetails(details);

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(InvalidException.class)
    public ResponseEntity<Object> handleMethodInvalidException(
            InvalidException ex, WebRequest request) {
        ErrorDepscriptionDetail error = new ErrorDepscriptionDetail();
        error.setError(ex.getMessage());
        List<String> details = new ArrayList<>();
        details.add("Tuổi cần lớn hơn 18");
        error.setDetails(details);

        return new ResponseEntity<>(error, HttpStatus.BAD_GATEWAY);
    }
    
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolationException(
            DataIntegrityViolationException ex, WebRequest request) {
        
        ErrorDepscriptionDetail error = new ErrorDepscriptionDetail();
        error.setError(ex.getMessage());
        
        List<String> details = new ArrayList<>();
        details.add("Cannot perform the operation due to foreign key constraint or other data integrity issues.");
        error.setDetails(details);
        
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handleException(Exception ex) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setStatus(400);
        apiResponse.setMessage(ex.getMessage());
//        apiResponse.setData(null);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
    }
}
