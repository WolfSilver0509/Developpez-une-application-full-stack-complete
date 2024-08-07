package com.openclassrooms.mddapi.exeptions;

public class ValidationException extends RuntimeException {

    /**
     * Ceci est une exception personnalisée qui est lancée lorsqu'une erreur de validation se produit.
     * @param message
     */
    public ValidationException(String message) {
        super(message);
    }
}
