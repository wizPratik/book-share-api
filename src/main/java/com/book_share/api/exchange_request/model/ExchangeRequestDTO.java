package com.book_share.api.exchange_request.model;

import com.book_share.api.config.AppConfig;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ExchangeRequestDTO {

    private Long id;

    @NotNull
    @ExchangeRequestUidUnique
    private UUID uid;

    @NotNull
    @Size(max = AppConfig.STRING_MAX_LENGTH)
    private String location;

    @NotNull
    private Integer duration;

    @NotNull
    private RequestStatus status;

    @NotNull
    private Long byUser;

    @NotNull
    private Long toUser;

    @NotNull
    @ExchangeRequestUserBookUnique
    private Long userBook;

}
