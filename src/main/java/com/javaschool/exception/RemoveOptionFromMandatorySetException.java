package com.javaschool.exception;

public class RemoveOptionFromMandatorySetException extends Exception{

    public RemoveOptionFromMandatorySetException() {

        super("Try to delete option from mandatory set ");
    }
}
