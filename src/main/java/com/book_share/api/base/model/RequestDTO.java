package com.book_share.api.base.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

/**
 * JSON => POJO.
 */
@JsonAutoDetect(
    fieldVisibility = Visibility.NONE,
    getterVisibility = Visibility.PUBLIC_ONLY,
    setterVisibility = Visibility.PUBLIC_ONLY)
public interface RequestDTO extends DTO {

}
