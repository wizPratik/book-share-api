package com.book_share.api.exchange_request.entity;

import com.book_share.api.base.entity.UniqueAuditedEntity;
import com.book_share.api.exchange_request.model.RequestStatus;
import com.book_share.api.user.entity.User;
import com.book_share.api.user_book_collection.entity.UserBookCollection;
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
public class ExchangeRequest extends UniqueAuditedEntity {

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private Integer duration;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RequestStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "by_user_id", nullable = false)
    private User byUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_user_id", nullable = false)
    private User toUser;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_book_id", nullable = false, unique = true)
    private UserBookCollection userBook;

}
