package ru.petrov.messageserver.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.petrov.messageserver.dtos.request.MessageRequestDto;
import ru.petrov.messageserver.dtos.response.MessageResponseDto;
import ru.petrov.messageserver.entitys.ConversationRoom;
import ru.petrov.messageserver.entitys.Message;
import ru.petrov.messageserver.repositories.ConversationRoomRepository;
import ru.petrov.messageserver.repositories.MessageRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository repository;
    private final ConversationRoomRepository conversationRoomRepository;

    public MessageResponseDto save(MessageRequestDto messageDto, long roomId) {

        ConversationRoom room = conversationRoomRepository.getReferenceById(roomId);
        Message message = Message.builder()
                .text(messageDto.getText())
                .conversationRoom(room)
                .timeStamp(Timestamp.valueOf(LocalDateTime.now()))
                .likes(0)
                .owner(room.getUsername())
                .image(messageDto.getImage())
                .build();

        var saveMessage = repository.save(message);

        return MessageResponseDto.builder()
                .id(saveMessage.getId())
                .text(saveMessage.getText())
                .image(saveMessage.getImage())
                .owner(saveMessage.getOwner())
                .timestamp(saveMessage.getTimeStamp())
                .likes(saveMessage.getLikes())
                .build();
    }

    public Message getMessageById(Long messageId) {
        return repository.findWithJoinFetch(messageId).orElseThrow();
    }
}
