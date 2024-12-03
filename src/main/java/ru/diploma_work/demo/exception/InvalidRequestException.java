package ru.diploma_work.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Исключение, выбрасываемое в случае, когда передаваемые в теле запроса параметры не проходят валидацию (т.е. не удовлетворяют
 * требованиям, установленным документацией к API). Валидация реализуется {@link ru.diploma_work.demo.utils.ValidationUtils}
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidRequestException extends RuntimeException {

    public InvalidRequestException(String message) {
        super(message);
    }
}
