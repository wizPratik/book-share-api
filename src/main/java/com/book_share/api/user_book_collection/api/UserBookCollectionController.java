package com.book_share.api.user_book_collection.api;

import com.book_share.api.user_book_collection.model.UserBookCollectionDTO;
import com.book_share.api.user_book_collection.service.UserBookCollectionService;
import com.book_share.api.util.ReferencedException;
import com.book_share.api.util.ReferencedWarning;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(
    value = "/api/userBookCollections",
    produces = MediaType.APPLICATION_JSON_VALUE)
public final class UserBookCollectionController {

    private final UserBookCollectionService userBookCollectionService;

    public UserBookCollectionController(
        final UserBookCollectionService userBookCollectionService1) {
        this.userBookCollectionService = userBookCollectionService1;
    }

    @GetMapping
    public ResponseEntity<List<UserBookCollectionDTO>> getAllCollections() {
        return ResponseEntity.ok(userBookCollectionService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserBookCollectionDTO> getUserBookCollection(
        @PathVariable(name = "id") final Long id) {
        return ResponseEntity.ok(userBookCollectionService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createUserBookCollection(
        @RequestBody @Valid final UserBookCollectionDTO collectionDTO) {
        final Long createdId = userBookCollectionService.create(collectionDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateUserBookCollection(
        @PathVariable(name = "id") final Long id,
        @RequestBody @Valid final UserBookCollectionDTO userBookCollectionDTO) {
        userBookCollectionService.update(id, userBookCollectionDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteUserBookCollection(
        @PathVariable(name = "id") final Long id) {
        final ReferencedWarning referencedWarning =
            userBookCollectionService.getReferencedWarning(id);
        if (referencedWarning != null) {
            throw new ReferencedException(referencedWarning);
        }
        userBookCollectionService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
