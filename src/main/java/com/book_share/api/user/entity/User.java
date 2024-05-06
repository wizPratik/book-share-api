package com.book_share.api.user.entity;

import com.book_share.api.base.entity.UniqueAuditedEntity;
import com.book_share.api.user_book_collection.entity.UserBookCollection;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.OneToMany;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class User extends UniqueAuditedEntity {

    @Column(nullable = false)
    private String firstName;

    @Column
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String location;

    @OneToMany(mappedBy = "user")
    private Set<UserBookCollection> userBook;

}
