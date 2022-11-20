package ru.petrov.messageserver.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.petrov.messageserver.dtos.request.AccountRequestDto;
import ru.petrov.messageserver.dtos.request.CreateSecureUserDto;
import ru.petrov.messageserver.exeptionapi.exeptions.EmailAlreadyExistException;

@Service
@RequiredArgsConstructor
@Slf4j
public class SecureUserService {

    private final RestTemplate restTemplate;

    public boolean createSecureUser(AccountRequestDto accountRequestDto) {

        CreateSecureUserDto secureUserDto = new CreateSecureUserDto(accountRequestDto.getEmail(), accountRequestDto.getPassword());
        HttpEntity<?> entity = new HttpEntity<>(secureUserDto);

        try {

            log.info("Try create User: {}", accountRequestDto.getEmail());

            ResponseEntity<?> exchange = restTemplate
                    .exchange("http://localhost:9000/api/v1/create-user",
                            HttpMethod.POST, entity, String.class);

            return exchange.getStatusCodeValue() == 201;

        } catch (Exception e) {
            log.error("Create new Account failed: {}", e.toString());
            throw new EmailAlreadyExistException("Email already exist");
        }
    }

}
