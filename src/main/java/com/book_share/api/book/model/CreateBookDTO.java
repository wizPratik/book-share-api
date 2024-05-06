package com.book_share.api.book.model;

import com.book_share.api.config.AppConfig;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateBookDTO {

    @NotNull
    @Size(max = AppConfig.STRING_MAX_LENGTH)
    @BookTitleUnique
    private String title;

    @NotNull
    @Size(max = AppConfig.STRING_MAX_LENGTH)
    private String author;

    @NotNull
    private String genre;
}
