package com.book_share.api.user_book_collection.service;

import com.book_share.api.book.entity.Book;
import com.book_share.api.book.repos.BookRepository;
import com.book_share.api.exchange_request.entity.ExchangeRequest;
import com.book_share.api.exchange_request.repos.ExchangeRequestRepository;
import com.book_share.api.user.entity.User;
import com.book_share.api.user.repos.UserRepository;
import com.book_share.api.user_book_collection.entity.UserBookCollection;
import com.book_share.api.user_book_collection.model.UserBookCollectionDTO;
import com.book_share.api.user_book_collection.repos.UserBookCollectionRepository;
import com.book_share.api.util.NotFoundException;
import com.book_share.api.util.ReferencedWarning;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public final class UserBookCollectionService {

    private final UserBookCollectionRepository userBookCollectionRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final ExchangeRequestRepository exchangeRequestRepository;

    public UserBookCollectionService(
        final UserBookCollectionRepository userBookCollectionRepository1,
        final BookRepository bookRepository1,
        final UserRepository userRepository1,
        final ExchangeRequestRepository exchangeRequestRepository1) {
        this.userBookCollectionRepository = userBookCollectionRepository1;
        this.bookRepository = bookRepository1;
        this.userRepository = userRepository1;
        this.exchangeRequestRepository = exchangeRequestRepository1;
    }

    public List<UserBookCollectionDTO> findAll() {
        final List<UserBookCollection> userBookCollections =
            userBookCollectionRepository.findAll(Sort.by("id"));
        return userBookCollections.stream()
            .map(ubc -> mapToDTO(ubc, new UserBookCollectionDTO()))
            .toList();
    }

    public UserBookCollectionDTO get(final Long id) {
        return userBookCollectionRepository.findById(id)
            .map(ubc -> mapToDTO(ubc, new UserBookCollectionDTO()))
            .orElseThrow(NotFoundException::new);
    }

    public Long create(final UserBookCollectionDTO userBookCollectionDTO) {
        final UserBookCollection userBookCollection = new UserBookCollection();
        mapToEntity(userBookCollectionDTO, userBookCollection);
        return userBookCollectionRepository.save(userBookCollection).getId();
    }

    public void update(final Long id, final UserBookCollectionDTO ubcDTO) {
        final UserBookCollection userBookCollection =
            userBookCollectionRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(ubcDTO, userBookCollection);
        userBookCollectionRepository.save(userBookCollection);
    }

    public void delete(final Long id) {
        userBookCollectionRepository.deleteById(id);
    }

    private UserBookCollectionDTO mapToDTO(
        final UserBookCollection userBookCollection,
        final UserBookCollectionDTO dto) {
        dto.setId(userBookCollection.getId());
        dto.setBookCondition(userBookCollection.getBookCondition());
        dto.setAvailable(userBookCollection.getAvailable());
        dto.setLocation(userBookCollection.getLocation());
        dto.setBook(
            userBookCollection.getBook() == null
                ? null
                : userBookCollection.getBook().getId());
        dto.setUser(
            userBookCollection.getUser() == null
                ? null
                : userBookCollection.getUser().getId());
        return dto;
    }

    private UserBookCollection mapToEntity(
        final UserBookCollectionDTO dto,
        final UserBookCollection userBookCollection) {
        userBookCollection.setBookCondition(dto.getBookCondition());
        userBookCollection.setAvailable(dto.getAvailable());
        userBookCollection.setLocation(dto.getLocation());
        final Book book =
            dto.getBook() == null
                ? null
                : bookRepository.findById(dto.getBook())
                    .orElseThrow(() -> new NotFoundException("book not found"));
        userBookCollection.setBook(book);
        final User user =
            dto.getUser() == null
                ? null
                : userRepository.findById(dto.getUser())
                    .orElseThrow(() -> new NotFoundException("user not found"));
        userBookCollection.setUser(user);
        return userBookCollection;
    }

    public ReferencedWarning getReferencedWarning(final Long id) {
        final ReferencedWarning referencedWarning = new ReferencedWarning();
        final UserBookCollection userBookCollection =
            userBookCollectionRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        final ExchangeRequest userBookExchangeRequest =
            exchangeRequestRepository.findFirstByUserBook(
                userBookCollection);
        String rwk = "userBookCollection.exchangeRequest.userBook.referenced";
        if (userBookExchangeRequest != null) {
            referencedWarning.setKey(rwk);
            referencedWarning.addParam(userBookExchangeRequest.getId());
            return referencedWarning;
        }
        return null;
    }

}
