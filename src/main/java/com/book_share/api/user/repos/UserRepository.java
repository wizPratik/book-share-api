package com.book_share.api.user.repos;

import com.book_share.api.user.entity.User;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByUid(UUID uid);

    boolean existsByEmailIgnoreCase(String email);

    Optional<User> findByUid(UUID uid);

}
