package com.book_share.api.auth.service;

import com.book_share.api.user.entity.User;
import com.book_share.api.user.repos.UserRepository;
import com.book_share.api.util.NotFoundException;
import java.util.Objects;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public final class AuthService {

    private final UserRepository userRepository;

    public AuthService(final UserRepository userRepository1) {
        this.userRepository = userRepository1;
    }

    public boolean isCredentialsValid(final String uid, final String password) {
        User u =
            userRepository
                .findByUid(UUID.fromString(uid))
                .orElseThrow(NotFoundException::new);
        return Objects.equals(u.getPassword(), password);
    }
}
