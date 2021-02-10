package com.javaschool.exception.notFound;

public class ExamplesNotFoundException extends Exception{

    public ExamplesNotFoundException(long id) {
        super(String.format("Examples with Id %d not found", id));
    }

    public ExamplesNotFoundException(String name) {
        super(String.format("Examples with name %s not found", name));
    }
}
