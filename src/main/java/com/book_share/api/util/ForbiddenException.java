package com.book_share.api.util;

public class ForbiddenException extends RuntimeException {

    public ForbiddenException() {
        super();
    }

    public ForbiddenException(final String message) {
        super(message);
    }

}
