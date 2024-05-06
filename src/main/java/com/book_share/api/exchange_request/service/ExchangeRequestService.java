package com.book_share.api.exchange_request.service;

import com.book_share.api.base.constants.Msg;
import com.book_share.api.exchange_request.entity.ExchangeRequest;
import com.book_share.api.exchange_request.model.ExchangeRequestDTO;
import com.book_share.api.exchange_request.repos.ExchangeRequestRepository;
import com.book_share.api.user.entity.User;
import com.book_share.api.user.repos.UserRepository;
import com.book_share.api.user_book_collection.entity.UserBookCollection;
import com.book_share.api.user_book_collection.repos.UserBookCollectionRepository;
import com.book_share.api.util.NotFoundException;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public final class ExchangeRequestService {

    private final ExchangeRequestRepository exchangeRequestRepository;
    private final UserRepository userRepository;
    private final UserBookCollectionRepository userBookCollectionRepository;

    public ExchangeRequestService(
        final ExchangeRequestRepository exchangeRequestRepository1,
        final UserRepository userRepository1,
        final UserBookCollectionRepository userBookCollectionRepository1) {
        this.exchangeRequestRepository = exchangeRequestRepository1;
        this.userRepository = userRepository1;
        this.userBookCollectionRepository = userBookCollectionRepository1;
    }

    public List<ExchangeRequestDTO> findAll() {
        final List<ExchangeRequest> exchangeRequests =
            exchangeRequestRepository.findAll(Sort.by("id"));
        return exchangeRequests.stream()
            .map(e -> mapToDTO(e, new ExchangeRequestDTO()))
            .toList();
    }

    public ExchangeRequestDTO get(final Long id) {
        return exchangeRequestRepository.findById(id)
            .map(e -> mapToDTO(e, new ExchangeRequestDTO()))
            .orElseThrow(NotFoundException::new);
    }

    public Long create(final ExchangeRequestDTO exchangeRequestDTO) {
        final ExchangeRequest exchangeRequest = new ExchangeRequest();
        mapToEntity(exchangeRequestDTO, exchangeRequest);
        return exchangeRequestRepository.save(exchangeRequest).getId();
    }

    public void update(
        final Long id,
        final ExchangeRequestDTO exchangeRequestDTO) {
        final ExchangeRequest exchangeRequest =
            exchangeRequestRepository
                .findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(exchangeRequestDTO, exchangeRequest);
        exchangeRequestRepository.save(exchangeRequest);
    }

    public void delete(final Long id) {
        exchangeRequestRepository.deleteById(id);
    }

    private ExchangeRequestDTO mapToDTO(
        final ExchangeRequest exchangeRequest,
        final ExchangeRequestDTO dto) {
        dto.setId(exchangeRequest.getId());
        dto.setUid(exchangeRequest.getUid());
        dto.setLocation(exchangeRequest.getLocation());
        dto.setDuration(exchangeRequest.getDuration());
        dto.setStatus(exchangeRequest.getStatus());
        dto.setByUser(
            exchangeRequest.getByUser() == null
                ? null
                : exchangeRequest.getByUser().getId());
        dto.setToUser(
            exchangeRequest.getToUser() == null
                ? null
                : exchangeRequest.getToUser().getId());
        dto.setUserBook(
            exchangeRequest.getUserBook() == null
                ? null
                : exchangeRequest.getUserBook().getId());
        return dto;
    }

    private ExchangeRequest mapToEntity(
        final ExchangeRequestDTO dto,
        final ExchangeRequest exchangeRequest) {
        exchangeRequest.setLocation(dto.getLocation());
        exchangeRequest.setDuration(dto.getDuration());
        exchangeRequest.setStatus(dto.getStatus());
        final User byUser =
            dto.getByUser() == null
                ? null
                : userRepository.findById(dto.getByUser())
                    .orElseThrow(() -> new NotFoundException(Msg.BY_USER_404));
        exchangeRequest.setByUser(byUser);
        final User toUser =
            dto.getToUser() == null
                ? null
                : userRepository.findById(dto.getToUser())
                    .orElseThrow(() -> new NotFoundException(Msg.TO_USER_404));
        exchangeRequest.setToUser(toUser);
        final UserBookCollection userBook =
            dto.getUserBook() == null
                ? null
                : userBookCollectionRepository.findById(dto.getUserBook())
                    .orElseThrow(() -> new NotFoundException(Msg.USR_BOOK_404));
        exchangeRequest.setUserBook(userBook);
        return exchangeRequest;
    }

    public boolean uidExists(final UUID uid) {
        return exchangeRequestRepository.existsByUid(uid);
    }

    public boolean userBookExists(final Long id) {
        return exchangeRequestRepository.existsByUserBookId(id);
    }

}
