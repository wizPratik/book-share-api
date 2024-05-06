package com.book_share.api.exchange_request.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RequestStatus {

    CREATED("created"),
    APPROVED("approved"),
    REJECTED("rejected"),
    COMPLETED("completed"),
    UNDETERMINED("undetermined");

    private final String value;
}
