package ru.petrov.messageserver.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.petrov.messageserver.dtos.request.AccountRequestDto;
import ru.petrov.messageserver.dtos.response.AccountResponseDto;
import ru.petrov.messageserver.entitys.Account;
import ru.petrov.messageserver.repositories.AccountRepository;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountResponseDto createAccount(AccountRequestDto accountRequestDto) {

        Account account = Account.builder()
                .firstName(accountRequestDto.getFirstName())
                .lastName(accountRequestDto.getLastName())
                .email(accountRequestDto.getEmail())
                .dateOfBirth(accountRequestDto.getDateOfBirth())
                .gender(accountRequestDto.getGender())
                .phone(accountRequestDto.getPhone())
                .picture(accountRequestDto.getPicture())
                .registerDate(LocalDate.now())
                .build();

        Account saveAccount = accountRepository.save(account);

        return AccountResponseDto.builder()
                .id(saveAccount.getId())
                .firstName(saveAccount.getFirstName())
                .lastName(saveAccount.getLastName())
                .dateOfBirth(saveAccount.getDateOfBirth())
                .gender(saveAccount.getGender())
                .picture(saveAccount.getPicture())
                .friends(saveAccount.getFriends())
                .build();


    }

    public void addFriendInAccount(Long currentAccountId, Long friendId) {
        Account account = accountRepository.findById(currentAccountId).orElseThrow();
        Account friend = accountRepository.findById(friendId).orElseThrow();
        account.addFriend(friend);
        accountRepository.save(account);
    }

    public AccountResponseDto getAccountById(Long id) {
        Account saveAccount = accountRepository.findById(id).orElseThrow();

        return AccountResponseDto.builder()
                .id(saveAccount.getId())
                .firstName(saveAccount.getFirstName())
                .lastName(saveAccount.getLastName())
                .dateOfBirth(saveAccount.getDateOfBirth())
                .gender(saveAccount.getGender())
                .picture(saveAccount.getPicture())
                .friends(saveAccount.getFriends())
                .build();

    }
}
