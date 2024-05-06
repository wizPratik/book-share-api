package com.book_share.api.message.repos;

import com.book_share.api.message.entity.Message;
import com.book_share.api.user.entity.User;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MessageRepository extends JpaRepository<Message, Long> {

    Message findFirstByToUser(User user);

    boolean existsByUid(UUID uid);

}
