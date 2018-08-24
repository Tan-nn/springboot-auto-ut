/*
 * Copyright (c) 2016 NIPPON TELEGRAPH AND TELEPHONE EAST CORPORATION
 */
package com.example.common.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Created by tan on 03/04/2017.
 */
@ControllerAdvice
@RestController
public class CommonExceptionHandler {

    /**
     *
     * Handle error, response error
     *
     * @param request http
     * @param response http
     * @param ex Error will be Handle
     * @return ErrorResponse Response
     *
     */
    @ExceptionHandler(ErrorResponseException.class)
    public ErrorResponse exceptionHandler(
            HttpServletRequest request,
            HttpServletResponse response,
            ErrorResponseException ex) {

        response.setStatus(ex.getError().getStatus());
        ErrorResponse result = ex.getError();

        return result;
    }

    /**
     *
     * Handle error, response error
     *
     * @param request http
     * @param response http
     * @param ex Error will be Handle
     * @return ErrorResponse Response
     *
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorResponse IllegalArgumentExceptionHandler(
            HttpServletRequest request,
            HttpServletResponse response,
            IllegalArgumentException ex) {

        response.setStatus(400);
        ErrorResponse result = new ErrorResponse();
        result.setStatus(400);
        result.setMessage(ex.getMessage());

        return result;
    }


}
