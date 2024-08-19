package com.screeningTest.screening.exception;

import com.screeningTest.screening.dto.request.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHanler {

    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiResponse> handlerAppExceptionException(AppException exception){
        ApiResponse apiResponse = new ApiResponse();
        //get enum đã truyền vào lúc throw exception
        ErrorCode errorCode = exception.getErrorCode();
        //get code error và message
       apiResponse.setCode(errorCode.getCode());
       apiResponse.setMessage(errorCode.getMessage());
       return ResponseEntity.badRequest().body(apiResponse);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ApiResponse> handlerMethodArgumentNotValidException(MethodArgumentNotValidException exception){
        //lay gia tri enum tu validation cua spring trong entity
        String enumKey = exception.getFieldError().getDefaultMessage();
        ErrorCode errorCode = ErrorCode.valueOf(enumKey);
        //gán giá trị error code và messge vào apiResponse từ enumkey đã lấy được
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(errorCode.getCode());
        apiResponse.setMessage(errorCode.getMessage());
        return ResponseEntity.badRequest().body(apiResponse);
    }
}
