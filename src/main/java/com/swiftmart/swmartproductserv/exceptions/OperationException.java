package com.swiftmart.swmartproductserv.exceptions;


// This is taken as Runtime, which is for scenarios where the exception is not expected to be recovered from
// and can be handled globally using exception handlers

public class OperationException extends RuntimeException{
    public OperationException(String message){
        super(message);
    }
}