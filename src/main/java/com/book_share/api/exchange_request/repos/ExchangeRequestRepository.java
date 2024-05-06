package com.book_share.api.exchange_request.repos;

import com.book_share.api.exchange_request.entity.ExchangeRequest;
import com.book_share.api.user.entity.User;
import com.book_share.api.user_book_collection.entity.UserBookCollection;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ExchangeRequestRepository extends JpaRepository<ExchangeRequest, Long> {

    ExchangeRequest findFirstByByUser(User user);

    ExchangeRequest findFirstByToUser(User user);

    ExchangeRequest findFirstByUserBook(UserBookCollection userBookCollection);

    boolean existsByUid(UUID uid);

    boolean existsByUserBookId(Long id);

}
