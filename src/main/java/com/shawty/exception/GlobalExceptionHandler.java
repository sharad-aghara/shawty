package com.shawty.exception;

import com.shawty.entity.ApiError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGeneral(
            Exception ex
            , HttpServletRequest req
    ) {
        ApiError error = new ApiError(
                HttpStatus.INTERNAL_SERVER_ERROR.value()
                , "GEH Message: " + ex.getMessage()
                , "Something went wrong"
                , req.getRequestURI()
        );

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(error);
    }
}
