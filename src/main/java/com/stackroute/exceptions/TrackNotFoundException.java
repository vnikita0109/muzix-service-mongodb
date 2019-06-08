package com.stackroute.exceptions;

public class TrackNotFoundException extends Exception{
    private String message;

    //Exception if track does not exist in database
    public TrackNotFoundException(String msg){
        this.message=msg;
    }
}
