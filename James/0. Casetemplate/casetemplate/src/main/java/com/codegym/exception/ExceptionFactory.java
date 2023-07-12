package com.codegym.exception;

public class ExceptionFactory{
    public static NumberInputException getNumberInputException(String type, String message, Long cardId) {
        if (type.equals("WEB")) {
            return new NumberInputExceptionWeb("WEB: " + message, cardId);
        }else{
            return new NumberInputExceptionApi("API: " + message, cardId);
        }
    }
}
