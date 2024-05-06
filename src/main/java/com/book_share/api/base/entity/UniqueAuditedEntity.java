package com.book_share.api.base.entity;

import com.book_share.api.util.UniqueIdGenerator;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class UniqueAuditedEntity extends AuditedEntity {

    @Column(nullable = false, unique = true, columnDefinition = "char(36)")
    @Setter(AccessLevel.NONE)
    private UUID uid = UniqueIdGenerator.v7();
}
