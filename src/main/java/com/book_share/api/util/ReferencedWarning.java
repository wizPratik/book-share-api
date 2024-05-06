package com.book_share.api.util;

import java.util.ArrayList;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public final class ReferencedWarning {

    private String key = null;
    private ArrayList<Object> params = new ArrayList<>();

    public void addParam(final Object param) {
        params.add(param);
    }

    public String toMessage() {
        String message = key;
        if (!params.isEmpty()) {
            message += "," + params.stream()
                    .map(Object::toString)
                    .collect(Collectors.joining(","));
        }
        return message;
    }

}
