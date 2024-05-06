package com.book_share.api.message.service;

import com.book_share.api.base.constants.Msg;
import com.book_share.api.message.entity.Message;
import com.book_share.api.message.model.MessageDTO;
import com.book_share.api.message.repos.MessageRepository;
import com.book_share.api.user.entity.User;
import com.book_share.api.user.repos.UserRepository;
import com.book_share.api.util.NotFoundException;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public final class MessageService {

    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    public MessageService(
        final MessageRepository messageRepository1,
        final UserRepository userRepository1) {
        this.messageRepository = messageRepository1;
        this.userRepository = userRepository1;
    }

    public List<MessageDTO> findAll() {
        final List<Message> messages =
            messageRepository.findAll(Sort.by("id"));
        return messages.stream()
            .map(message -> mapToDTO(message, new MessageDTO()))
            .toList();
    }

    public MessageDTO get(final Long id) {
        return messageRepository.findById(id)
            .map(message -> mapToDTO(message, new MessageDTO()))
            .orElseThrow(NotFoundException::new);
    }

    public Long create(final MessageDTO messageDTO) {
        final Message message = new Message();
        mapToEntity(messageDTO, message);
        return messageRepository.save(message).getId();
    }

    public void update(final Long id, final MessageDTO messageDTO) {
        final Message message = messageRepository.findById(id)
            .orElseThrow(NotFoundException::new);
        mapToEntity(messageDTO, message);
        messageRepository.save(message);
    }

    public void delete(final Long id) {
        messageRepository.deleteById(id);
    }

    private MessageDTO mapToDTO(
        final Message message,
        final MessageDTO dto) {
        dto.setId(message.getId());
        dto.setUid(message.getUid());
        dto.setText(message.getText());
        dto.setToUser(
            message.getToUser() == null
                ? null
                : message.getToUser().getId());
        return dto;
    }

    private Message mapToEntity(
        final MessageDTO dto,
        final Message message) {
        message.setText(dto.getText());
        final User toUser =
            dto.getToUser() == null
                ? null
                : userRepository.findById(dto.getToUser())
                    .orElseThrow(() -> new NotFoundException(Msg.TO_USER_404));
        message.setToUser(toUser);
        return message;
    }

    public boolean uidExists(final UUID uid) {
        return messageRepository.existsByUid(uid);
    }

}
