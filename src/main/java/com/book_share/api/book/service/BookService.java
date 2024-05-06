package com.book_share.api.book.service;

import com.book_share.api.book.entity.Book;
import com.book_share.api.book.model.BookDTO;
import com.book_share.api.book.model.CreateBookDTO;
import com.book_share.api.book.model.FetchBookDTO;
import com.book_share.api.book.model.UpdateBookDTO;
import com.book_share.api.book.repos.BookRepository;
import com.book_share.api.user_book_collection.entity.UserBookCollection;
import com.book_share.api.user_book_collection.repos.UserBookCollectionRepository;
import com.book_share.api.util.EnumMapper;
import com.book_share.api.util.NotFoundException;
import com.book_share.api.util.ReferencedWarning;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public final class BookService {

    private final BookRepository bookRepository;
    private final UserBookCollectionRepository userBookCollectionRepository;

    public BookService(
        final BookRepository bookRepository1,
        final UserBookCollectionRepository userBookCollectionRepository1) {
        this.bookRepository = bookRepository1;
        this.userBookCollectionRepository = userBookCollectionRepository1;
    }

    public List<FetchBookDTO> fetchAllBooks() {
        final List<Book> books = bookRepository.findAll(Sort.by("id"));
        return books.stream().map(this::fetchDTOFromBook).toList();
    }

    public BookDTO get(final Long id) {
        return bookRepository.findById(id)
            .map(book -> mapToDTO(book, new BookDTO()))
            .orElseThrow(NotFoundException::new);
    }

    public FetchBookDTO fetch(final String uuid) {
        Book book = bookRepository.findByUid(UUID.fromString(uuid));
        if (book == null) {
            throw new NotFoundException();
        }
        return new FetchBookDTO(book);
    }

    public String createBook(final CreateBookDTO createBookDTO) {
        final Book book = createBookFromDTO(createBookDTO);
        return String.valueOf(bookRepository.save(book).getUid());
    }

    public void updateBook(
        final String uuid,
        final UpdateBookDTO updateBookDTO) {
        UUID uid = UUID.fromString(uuid);
        final Book book = bookRepository.findByUid(uid);
        if (book == null) {
            throw new NotFoundException();
        }
        Book updatedBook = updateBookFromDTO(updateBookDTO, book);
        bookRepository.save(updatedBook);
    }

    public void delete(final Long id) {
        bookRepository.deleteById(id);
    }

    private BookDTO mapToDTO(final Book book, final BookDTO dto) {
        dto.setId(book.getId());
        dto.setUid(book.getUid());
        dto.setTitle(book.getTitle());
        dto.setAuthor(book.getAuthor());
        dto.setGenre(book.getGenre());
        return dto;
    }

    private Book createBookFromDTO(final CreateBookDTO createBookDTO) {
        final Book book = new Book();
        book.setTitle(createBookDTO.getTitle());
        book.setAuthor(createBookDTO.getAuthor());
        book.setGenre(EnumMapper.getGenre(createBookDTO.getGenre()));
        return book;
    }

    private FetchBookDTO fetchDTOFromBook(final Book book) {
        FetchBookDTO fetchBookDTO = new FetchBookDTO();
        fetchBookDTO.setUid(String.valueOf(book.getUid()));
        fetchBookDTO.setTitle(book.getTitle());
        fetchBookDTO.setAuthor(book.getAuthor());
        fetchBookDTO.setGenre(book.getGenre().getValue());
        return fetchBookDTO;
    }

    private Book updateBookFromDTO(
        final UpdateBookDTO updateBookDTO,
        final Book book) {
        if (updateBookDTO.getTitle() != null) {
            book.setTitle(updateBookDTO.getTitle());
        }
        if (updateBookDTO.getAuthor() != null) {
            book.setAuthor(updateBookDTO.getAuthor());
        }
        if (updateBookDTO.getGenre() != null) {
            book.setGenre(EnumMapper.getGenre(updateBookDTO.getGenre()));
        }
        return book;
    }

    public boolean uidExists(final UUID uid) {
        return bookRepository.existsByUid(uid);
    }

    public boolean titleExists(final String title) {
        return bookRepository.existsByTitleIgnoreCase(title);
    }

    public ReferencedWarning getReferencedWarning(final Long id) {
        final ReferencedWarning referencedWarning = new ReferencedWarning();
        final Book book = bookRepository.findById(id)
            .orElseThrow(NotFoundException::new);
        final UserBookCollection bookUserBookCollection =
            userBookCollectionRepository.findFirstByBook(book);
        if (bookUserBookCollection != null) {
            referencedWarning.setKey("book.userBookCollection.book.referenced");
            referencedWarning.addParam(bookUserBookCollection.getId());
            return referencedWarning;
        }
        return null;
    }

}
