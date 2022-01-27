package com.abhinav.usersystem.exceptions;

import java.util.Date;

import com.abhinav.usersystem.entities.Error;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class UserControllerAdvisor {

    @ExceptionHandler(value = { UserProfileServiceException.class })
    @ResponseBody
    public ResponseEntity<Error> handleUserServiceException(UserProfileServiceException ex,
                                                             WebRequest request)
    {
        Error error = new Error(ex.toString(), new Date());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = { Exception.class })
    @ResponseBody
    public ResponseEntity<Error> handleAnyException(Exception ex, WebRequest request) {


        Error error = new Error(ex.toString(), new Date());

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
