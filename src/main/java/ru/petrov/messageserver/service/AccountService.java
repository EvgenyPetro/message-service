package ru.petrov.messageserver.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.petrov.messageserver.dtos.request.AccountRequestDto;
import ru.petrov.messageserver.dtos.response.AccountResponseDto;
import ru.petrov.messageserver.entitys.Account;
import ru.petrov.messageserver.exeptionapi.exeptions.EmailAlreadyExistException;
import ru.petrov.messageserver.repositories.AccountRepository;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountService {

    private final AccountRepository accountRepository;
    private final SecureUserService secureUserService;


    public AccountResponseDto createAccount(AccountRequestDto accountRequestDto) {

        try {
            if (accountRepository.existsAccountByEmail(accountRequestDto.getEmail())) {
                log.debug("Email : {} already exist", accountRequestDto.getEmail());
                throw new EmailAlreadyExistException("Email already exist");
            }

            boolean isCreatedSecureUser = secureUserService.createSecureUser(accountRequestDto);

            if (!isCreatedSecureUser) {
                throw new EmailAlreadyExistException("Email already exist");
            }


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
            log.info("Create account entity id: {}", saveAccount.getId());

            return AccountResponseDto.builder()
                    .id(saveAccount.getId())
                    .firstName(saveAccount.getFirstName())
                    .lastName(saveAccount.getLastName())
                    .dateOfBirth(saveAccount.getDateOfBirth())
                    .gender(saveAccount.getGender())
                    .picture(saveAccount.getPicture())
                    .friends(saveAccount.getFriends())
                    .build();


        } catch (RuntimeException ex) {
            log.error("Create new Account failed: {}", ex.toString());
            throw ex;
        }
    }

    public void addFriendInAccount(Long currentAccountId, Long friendId) {

        Account account = accountRepository
                .findById(currentAccountId)
                .orElseThrow(() ->
                        new UsernameNotFoundException("Account not found by Id: " + friendId));
        Account friend = accountRepository
                .findById(friendId)
                .orElseThrow(() ->
                        new UsernameNotFoundException("Account not found by Id: " + currentAccountId));

        account.addFriend(friend);
        accountRepository.save(account);
    }

    public AccountResponseDto getAccountById(Long id) {
        Account saveAccount = accountRepository
                .findById(id)
                .orElseThrow(() ->
                        new UsernameNotFoundException("Account not found by Id: " + id));

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
