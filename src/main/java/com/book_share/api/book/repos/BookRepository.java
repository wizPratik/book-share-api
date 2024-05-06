package com.book_share.api.book.repos;

import com.book_share.api.book.entity.Book;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BookRepository extends JpaRepository<Book, Long> {

    boolean existsByUid(UUID uid);

    boolean existsByTitleIgnoreCase(String title);

    Book findByUid(UUID uid);

}
