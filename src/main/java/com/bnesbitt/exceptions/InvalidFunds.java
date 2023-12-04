package com.bnesbitt.exceptions;

public class InvalidFunds extends Exception {
    public InvalidFunds(String error) {
        super(error);
    }
}
