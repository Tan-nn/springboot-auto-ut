/*
 * Copyright (c) 2016 NIPPON TELEGRAPH AND TELEPHONE EAST CORPORATION
 */
package com.example.common.handler;

/**
 * Created by tan on 03/04/2017.
 */
public class ErrorResponseException extends RuntimeException {

    /**
     * default
     */
    private static final long serialVersionUID = 1L;


    private ErrorResponse error;

    /**
     * @return ErrorResponseException
     */
    public ErrorResponseException(ErrorResponse result) {
        super();
        error = result;
    }

    /**
     * @return  error
     */
    public ErrorResponse getError() {
        return this.error;
    }
}
