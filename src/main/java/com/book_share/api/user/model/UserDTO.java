package com.book_share.api.user.model;

import com.book_share.api.config.AppConfig;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserDTO {

    private Long id;

    @NotNull
    @UserUidUnique
    private UUID uid;

    @NotNull
    @Size(max = AppConfig.STRING_MAX_LENGTH)
    private String firstName;

    @Size(max = AppConfig.STRING_MAX_LENGTH)
    private String lastName;

    @NotNull
    @Size(max = AppConfig.STRING_MAX_LENGTH)
    @UserEmailUnique
    private String email;

    @NotNull
    @Size(max = AppConfig.STRING_MAX_LENGTH)
    private String password;

    @NotNull
    @Size(max = AppConfig.STRING_MAX_LENGTH)
    private String location;

}
