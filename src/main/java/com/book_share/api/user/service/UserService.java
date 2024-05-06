package com.book_share.api.user.service;

import com.book_share.api.exchange_request.entity.ExchangeRequest;
import com.book_share.api.exchange_request.repos.ExchangeRequestRepository;
import com.book_share.api.message.entity.Message;
import com.book_share.api.message.repos.MessageRepository;
import com.book_share.api.user.entity.User;
import com.book_share.api.user.model.UserDTO;
import com.book_share.api.user.repos.UserRepository;
import com.book_share.api.user_book_collection.entity.UserBookCollection;
import com.book_share.api.user_book_collection.repos.UserBookCollectionRepository;
import com.book_share.api.util.NotFoundException;
import com.book_share.api.util.ReferencedWarning;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public final class UserService {

    private final UserRepository userRepository;
    private final ExchangeRequestRepository exchangeRequestRepository;
    private final MessageRepository messageRepository;
    private final UserBookCollectionRepository userBookCollectionRepository;

    public UserService(
        final UserRepository userRepository1,
        final ExchangeRequestRepository exchangeRequestRepository1,
        final MessageRepository messageRepository1,
        final UserBookCollectionRepository userBookCollectionRepository1) {
        this.userRepository = userRepository1;
        this.exchangeRequestRepository = exchangeRequestRepository1;
        this.messageRepository = messageRepository1;
        this.userBookCollectionRepository = userBookCollectionRepository1;
    }

    public List<UserDTO> findAll() {
        final List<User> users = userRepository.findAll(Sort.by("id"));
        return users.stream()
            .map(user -> mapToDTO(user, new UserDTO()))
            .toList();
    }

    public UserDTO get(final Long id) {
        return userRepository.findById(id)
            .map(user -> mapToDTO(user, new UserDTO()))
            .orElseThrow(NotFoundException::new);
    }

    public Long create(final UserDTO userDTO) {
        final User user = new User();
        mapToEntity(userDTO, user);
        return userRepository.save(user).getId();
    }

    public void update(final Long id, final UserDTO userDTO) {
        final User user = userRepository.findById(id)
            .orElseThrow(NotFoundException::new);
        mapToEntity(userDTO, user);
        userRepository.save(user);
    }

    public void delete(final Long id) {
        userRepository.deleteById(id);
    }

    private UserDTO mapToDTO(final User user, final UserDTO dto) {
        dto.setId(user.getId());
        dto.setUid(user.getUid());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());
        dto.setLocation(user.getLocation());
        return dto;
    }

    private User mapToEntity(final UserDTO dto, final User user) {
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setLocation(dto.getLocation());
        return user;
    }

    public boolean uidExists(final UUID uid) {
        return userRepository.existsByUid(uid);
    }

    public boolean emailExists(final String email) {
        return userRepository.existsByEmailIgnoreCase(email);
    }

    public ReferencedWarning getReferencedWarning(final Long id) {
        final ReferencedWarning referencedWarning = new ReferencedWarning();
        final User user = userRepository.findById(id)
            .orElseThrow(NotFoundException::new);
        final ExchangeRequest byUserExchangeRequest =
            exchangeRequestRepository.findFirstByByUser(user);
        if (byUserExchangeRequest != null) {
            referencedWarning.setKey("user.exchangeRequest.byUser.referenced");
            referencedWarning.addParam(byUserExchangeRequest.getId());
            return referencedWarning;
        }
        final ExchangeRequest toUserExchangeRequest =
            exchangeRequestRepository.findFirstByToUser(user);
        if (toUserExchangeRequest != null) {
            referencedWarning.setKey("user.exchangeRequest.toUser.referenced");
            referencedWarning.addParam(toUserExchangeRequest.getId());
            return referencedWarning;
        }
        final Message toUserMessage = messageRepository.findFirstByToUser(user);
        if (toUserMessage != null) {
            referencedWarning.setKey("user.message.toUser.referenced");
            referencedWarning.addParam(toUserMessage.getId());
            return referencedWarning;
        }
        final UserBookCollection userUserBookCollection =
            userBookCollectionRepository.findFirstByUser(user);
        if (userUserBookCollection != null) {
            referencedWarning.setKey("user.userBookCollection.user.referenced");
            referencedWarning.addParam(userUserBookCollection.getId());
            return referencedWarning;
        }
        return null;
    }

}
