package com.bnesbitt.exceptions;

public class InsufficientFunds extends Exception {
    public InsufficientFunds(String error) {
        super(error);
    }
}
