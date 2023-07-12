package com.codegym.exception;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class NumberInputExceptionApi extends NumberInputException {
    private Long cardId;

    public NumberInputExceptionApi(String message, Long cardId) {
        super(message);
        this.cardId = cardId;
    }
}

