package com.book_share.api.util;

public class InternalServerException extends RuntimeException {

    public InternalServerException() {
        super();
    }

    public InternalServerException(final String message) {
        super(message);
    }

}
