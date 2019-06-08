package com.stackroute.exceptions;

public class TrackAlreadyExistsException extends Exception {
    private String message;

    //Exception if track already exists in database
    public TrackAlreadyExistsException(String msg){
        this.message=msg;
    }
}
