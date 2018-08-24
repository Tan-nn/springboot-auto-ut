/*
 * Copyright (c) 2016 NIPPON TELEGRAPH AND TELEPHONE EAST CORPORATION
 */
package com.example.common.handler;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by tan on 03/04/2017.
 */
public class ErrorResponse {

    @JsonProperty("status")
    private int status;

    @JsonProperty("message")
    private String message;

    /**
     * @return the status_cd
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status
     *            the statusCd to set
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message
     *            the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

}
