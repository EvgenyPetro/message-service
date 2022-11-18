package ru.petrov.messageserver.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.petrov.messageserver.dtos.request.MessageRequestDto;
import ru.petrov.messageserver.dtos.response.MessageResponseDto;
import ru.petrov.messageserver.entitys.Message;
import ru.petrov.messageserver.service.MessageService;

@RestController
@RequestMapping("/v1/message")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService service;

    @PostMapping("/create")
    public ResponseEntity<MessageResponseDto> createMessage(
            @RequestBody MessageRequestDto messageDto,
            @RequestParam(value = "roomId") Long roomId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(messageDto, roomId));
    }

    @GetMapping("/{messageId}")
    public ResponseEntity<Message> getMessageById(@PathVariable("messageId") Long messageId) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getMessageById(messageId));
    }

}
