package com.stackroute.exceptions;

public class TrackAlreadyExistsException extends Exception {
    private String message;

    public TrackAlreadyExistsException(String msg){
        this.message=msg;
    }
}
