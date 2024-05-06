package com.book_share.api.book.model;

import com.book_share.api.book.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FetchBookDTO {

    private String uid;

    private String title;

    private String author;

    private String genre;

    public FetchBookDTO(final Book book) {
        this.setUid(book.getUid().toString());
        this.setTitle(book.getTitle());
        this.setAuthor(book.getAuthor());
        this.setGenre(book.getGenre().getValue());
    }
}
