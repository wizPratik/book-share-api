package com.book_share.api.util;

import com.book_share.api.book.model.Genre;
import com.book_share.api.exchange_request.model.RequestStatus;
import com.book_share.api.user_book_collection.model.BookCondition;
import java.util.List;
import java.util.Optional;

public final class EnumMapper {

    private EnumMapper() {
        // Utility class, hence no instantiation
    }

    public static Genre getGenre(final String s) {
        List<Genre> genres = List.of(Genre.values());
        Optional<Genre> matchingGenre =
            genres.stream().filter(g -> g.getValue().equals(s)).findFirst();
        return matchingGenre.orElse(Genre.GENERAL);
    }

    public static RequestStatus getRequestStatus(final String s) {
        List<RequestStatus> statuses = List.of(RequestStatus.values());
        Optional<RequestStatus> matchingStatus =
            statuses.stream().filter(r -> r.getValue().equals(s)).findFirst();
        return matchingStatus.orElse(RequestStatus.UNDETERMINED);
    }

    public static BookCondition getBookCondition(final String s) {
        List<BookCondition> conditions = List.of(BookCondition.values());
        Optional<BookCondition> matchingCondition =
            conditions.stream().filter(c -> c.getValue().equals(s)).findFirst();
        return matchingCondition.orElse(BookCondition.OKAY);
    }

}
