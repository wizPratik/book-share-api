package com.book_share.api.user_book_collection.repos;

import com.book_share.api.book.entity.Book;
import com.book_share.api.user.entity.User;
import com.book_share.api.user_book_collection.entity.UserBookCollection;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserBookCollectionRepository extends JpaRepository<UserBookCollection, Long> {

    UserBookCollection findFirstByBook(Book book);

    UserBookCollection findFirstByUser(User user);

}
