package ru.petrov.messageserver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.petrov.messageserver.entitys.ConversationRoom;

public interface ConversationRoomRepository extends JpaRepository<ConversationRoom, Long> {
}
