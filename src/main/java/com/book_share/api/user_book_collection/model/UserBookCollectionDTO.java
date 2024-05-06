package com.book_share.api.user_book_collection.model;

import com.book_share.api.config.AppConfig;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserBookCollectionDTO {

    private Long id;

    @NotNull
    private BookCondition bookCondition;

    @NotNull
    private Boolean available;

    @NotNull
    @Size(max = AppConfig.STRING_MAX_LENGTH)
    private String location;

    @NotNull
    private Long book;

    @NotNull
    private Long user;

}
