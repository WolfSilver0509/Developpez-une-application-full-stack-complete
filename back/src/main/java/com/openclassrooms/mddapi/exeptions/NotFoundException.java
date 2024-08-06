package com.openclassrooms.mddapi.exeptions;

public class NotFoundException extends RuntimeException {

    /**
     * Ceci est une exception personnalisée qui est lancée lorsqu'un Post n'est pas trouvé.
     * @param message
     */
    public NotFoundException(String message) {
        super(message);
    }
}
