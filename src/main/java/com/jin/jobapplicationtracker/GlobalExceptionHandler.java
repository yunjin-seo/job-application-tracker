package com.jin.jobapplicationtracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
@RestControllerAdvice 
public class GlobalExceptionHandler {
    
    @ExceptionHandler (IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentExcdeption(
        IllegalArgumentException exception){
            
        ErrorResponse response = new ErrorResponse(exception.getMessage());
        
        //404 Not found
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(response);

    }

    @ExceptionHandler (MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(
        MethodArgumentNotValidException exception){
        
            String message = exception
                    .getBindingResult()     // 검증 결과 가져오기
                    .getFieldErrors()       // 실패한 필드 목록 가져오기
                    .get(0)          // 첫 번째 오류 가져오기
                    .getDefaultMessage();   // 직접 작성한 오류 메시지 가져오기

            ErrorResponse response = new ErrorResponse(message);

            return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);

        }
        @ExceptionHandler(MethodArgumentTypeMismatchException.class)
        public ResponseEntity<ErrorResponse> handleTypeMismatchException(
            MethodArgumentTypeMismatchException exception) {
            
            String message ="Invalid status value";
            
            ErrorResponse response = new ErrorResponse(message);

            return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);

        
        }
}
