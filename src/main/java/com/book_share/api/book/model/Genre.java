package com.book_share.api.book.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Genre {

    ACTION_AND_ADVENTURE("Action & Adventure"),
    HISTORY("History"),
    CLASSIC("Classic"),
    NON_FICTION("Non-fiction"),
    FICTION("Fiction"),
    ACADEMIA("Academia"),
    MEMOIR("Memoir"),
    GENERAL("General");

    private final String value;

}
