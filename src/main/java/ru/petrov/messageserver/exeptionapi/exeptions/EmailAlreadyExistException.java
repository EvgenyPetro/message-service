package ru.petrov.messageserver.exeptionapi.exeptions;

public class EmailAlreadyExistException extends RuntimeException{
    public EmailAlreadyExistException() {
    }

    public EmailAlreadyExistException(String message) {
        super(message);
    }

    public EmailAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmailAlreadyExistException(Throwable cause) {
        super(cause);
    }


}
