package com.book_share.api.user_book_collection.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BookCondition {

    GOOD("good"),
    OKAY("okay"),
    POOR("poor");

    private final String value;
}
