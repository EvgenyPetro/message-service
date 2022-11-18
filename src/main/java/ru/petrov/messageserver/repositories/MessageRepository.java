package ru.petrov.messageserver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.petrov.messageserver.entitys.Message;

import java.util.Optional;

public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query("select m from Message m join fetch m.conversationRoom where m.id=:id")
    Optional<Message> findWithJoinFetch(Long id);
}
