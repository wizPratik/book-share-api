package com.book_share.api.user_book_collection.entity;

import com.book_share.api.base.entity.AuditedEntity;
import com.book_share.api.book.entity.Book;
import com.book_share.api.exchange_request.entity.ExchangeRequest;
import com.book_share.api.user.entity.User;
import com.book_share.api.user_book_collection.model.BookCondition;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class UserBookCollection extends AuditedEntity {

    @Column(nullable = false, name = "\"condition\"")
    @Enumerated(EnumType.STRING)
    private BookCondition bookCondition;

    @Column(nullable = false)
    private Boolean available;

    @Column(nullable = false)
    private String location;

    @OneToOne(mappedBy = "userBook", fetch = FetchType.LAZY)
    private ExchangeRequest request;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}
