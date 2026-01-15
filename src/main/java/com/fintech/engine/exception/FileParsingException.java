package com.fintech.engine.exception;

public class FileParsingException extends RuntimeException {
    public FileParsingException(String msg) {
        super(msg);
    }

    public FileParsingException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
