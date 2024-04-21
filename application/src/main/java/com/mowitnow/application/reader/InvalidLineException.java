package com.mowitnow.application.reader;

public class InvalidLineException extends RuntimeException {

    public InvalidLineException(String line) {
        super(line);
    }
}
