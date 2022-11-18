package ru.petrov.messageserver.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageRequestDto {
    private String text;
    private String image;
    private int likes;
    private String owner;
}
