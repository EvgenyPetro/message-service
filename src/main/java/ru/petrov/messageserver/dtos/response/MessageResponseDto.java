package ru.petrov.messageserver.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageResponseDto {

    private Long id;
    private String text;
    private Timestamp timestamp;
    private String image;
    private int likes;
    private String owner;
}
