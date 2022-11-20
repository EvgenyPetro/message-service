package ru.petrov.messageserver.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.petrov.messageserver.entitys.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

    boolean existsAccountByEmail(String email);
}
