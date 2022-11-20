package ru.petrov.messageserver.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountRequestDto {

    private String firstName;
    private String lastName;
    private String gender;
    private String email;
    private LocalDate dateOfBirth;
    private String phone;
    private String picture;
    private String password;
}
