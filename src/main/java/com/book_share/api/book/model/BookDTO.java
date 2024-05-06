package com.book_share.api.book.model;

import com.book_share.api.config.AppConfig;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class BookDTO {

    private Long id;

    @NotNull
    @BookUidUnique
    private UUID uid;

    @NotNull
    @Size(max = AppConfig.STRING_MAX_LENGTH)
    @BookTitleUnique
    private String title;

    @NotNull
    @Size(max = AppConfig.STRING_MAX_LENGTH)
    private String author;

    @NotNull
    private Genre genre;

}
