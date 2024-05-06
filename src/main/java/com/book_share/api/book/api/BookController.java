package com.book_share.api.book.api;

import com.book_share.api.base.api.BaseController;
import com.book_share.api.book.model.CreateBookDTO;
import com.book_share.api.book.model.FetchBookDTO;
import com.book_share.api.book.model.UpdateBookDTO;
import com.book_share.api.book.service.BookService;
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
    value = "/api/books",
    produces = MediaType.APPLICATION_JSON_VALUE)
public final class BookController implements BaseController {

    private final BookService bookService;

    public BookController(final BookService bookService1) {
        this.bookService = bookService1;
    }

    @GetMapping
    public ResponseEntity<List<FetchBookDTO>> getAllBooks() {
        return ResponseEntity.ok(bookService.fetchAllBooks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FetchBookDTO> getBook(
        @PathVariable(name = "id") final String id) {
        return ResponseEntity.ok(bookService.fetch(id));
    }

    @PostMapping

    @ApiResponse(responseCode = "201")
    public ResponseEntity<String> createBook(
        @RequestBody @Valid final CreateBookDTO createBookDTO) {
        final String createdUid = bookService.createBook(createBookDTO);
        return new ResponseEntity<>(createdUid, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateBook(
        @PathVariable(name = "id") final String id,
        @RequestBody @Valid final UpdateBookDTO updateBookDTO) {
        bookService.updateBook(id, updateBookDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteBook(
        @PathVariable(name = "id") final Long id) {
        final ReferencedWarning referencedWarning =
            bookService.getReferencedWarning(id);
        if (referencedWarning != null) {
            throw new ReferencedException(referencedWarning);
        }
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
