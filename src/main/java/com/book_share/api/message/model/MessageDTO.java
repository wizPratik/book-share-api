package com.book_share.api.message.model;

import com.book_share.api.config.AppConfig;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class MessageDTO {

    private Long id;

    @NotNull
    @MessageUidUnique
    private UUID uid;

    @NotNull
    @Size(max = AppConfig.STRING_MAX_LENGTH)
    private String text;

    @NotNull
    private Long toUser;

}
