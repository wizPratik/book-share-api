package com.book_share.api.base.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

/**
 * POJO => JSON.
 */
@JsonAutoDetect(
    fieldVisibility = Visibility.NONE,
    getterVisibility = Visibility.PUBLIC_ONLY,
    setterVisibility = Visibility.NONE)
public interface ResponseDTO extends SerializableDTO {

}
