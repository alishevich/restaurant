package org.example.util.exception;

public class IllegalVoteTimeException extends RuntimeException{
    public IllegalVoteTimeException(String message) {
        super(message);
    }
}
