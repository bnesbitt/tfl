package com.iotics.exceptions;

public class InvalidFunds extends Exception {
    public InvalidFunds(String error) {
        super(error);
    }
}
