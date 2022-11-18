package ru.petrov.messageserver.entitys;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "messg")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String text;

    private Timestamp timeStamp;

    private String image;

    private int likes;

    private String owner;

    @ManyToOne(fetch = FetchType.LAZY)
    private ConversationRoom conversationRoom;

}
