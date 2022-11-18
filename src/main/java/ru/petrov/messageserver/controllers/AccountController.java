package ru.petrov.messageserver.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.petrov.messageserver.dtos.request.AccountRequestDto;
import ru.petrov.messageserver.dtos.response.AccountResponseDto;
import ru.petrov.messageserver.service.AccountService;

@RestController
@RequestMapping("v1/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/create")
    public ResponseEntity<AccountResponseDto> createAccount(@RequestBody AccountRequestDto accountRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(accountService.createAccount(accountRequestDto));
    }

    @PostMapping("/addfriend")
    public ResponseEntity<Void> addFriend(@Param("currentId") Long currentId,
                                          @Param("friendId") Long friendId) {
        accountService.addFriendInAccount(currentId, friendId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountResponseDto> getAccountById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(accountService.getAccountById(id));
    }
}
