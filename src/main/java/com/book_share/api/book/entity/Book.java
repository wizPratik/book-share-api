package com.book_share.api.book.entity;

import com.book_share.api.base.entity.UniqueAuditedEntity;
import com.book_share.api.book.model.Genre;
import com.book_share.api.user_book_collection.entity.UserBookCollection;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Book extends UniqueAuditedEntity {

    @Column(nullable = false, unique = true)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Genre genre;

    @OneToMany(mappedBy = "book")
    private Set<UserBookCollection> userBook;

}
