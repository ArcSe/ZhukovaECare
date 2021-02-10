package com.javaschool.exception.notFound;


public class NotDataFoundException extends Exception {

    public NotDataFoundException(String className) {

        super(String.format("No data found from %s", className));
    }
}
