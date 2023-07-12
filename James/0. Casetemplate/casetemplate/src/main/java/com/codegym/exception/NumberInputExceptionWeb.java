package com.codegym.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NumberInputExceptionWeb extends NumberInputException {
    private Long cardId;

    public NumberInputExceptionWeb(String message, Long cardId) {
        super(message);
        this.cardId = cardId;
    }
}
