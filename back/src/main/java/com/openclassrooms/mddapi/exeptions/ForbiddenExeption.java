package com.openclassrooms.mddapi.exeptions;

public class ForbiddenExeption extends RuntimeException {

    /**
     * Ceci est une exception personnalisée qui est lancée lorsqu'un Post n'est pas trouvé.
     * @param message
     */
    public ForbiddenExeption(String message) {
        super(message);
    }
}
