package ru.petrov.messageserver.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.petrov.messageserver.entitys.ConversationRoom;
import ru.petrov.messageserver.repositories.ConversationRoomRepository;

@RestController
public class ConversationController {

    private final ConversationRoomRepository repository;

    public ConversationController(ConversationRoomRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/create")
    public ConversationRoom conversationRoom(@RequestBody ConversationRoom conversationRoom, Authentication authentication) {
        conversationRoom.setUsername(authentication.getName());
        return repository.save(conversationRoom);
    }
}
