package com.pazarlamacitakip.pazarlamaci_backend.exception;

/**
 * Yetkisiz erişim denemesi durumunda fırlatılacak özel exception.
 */
public class UnauthorizedException extends RuntimeException {

    public UnauthorizedException(String message) {
        super(message);
    }
}
