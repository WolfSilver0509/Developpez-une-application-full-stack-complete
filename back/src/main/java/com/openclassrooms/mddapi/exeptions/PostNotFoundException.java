package com.openclassrooms.mddapi.exeptions;

public class PostNotFoundException extends RuntimeException {

    /**
     * Ceci est une exception personnalisée qui est lancée lorsqu'un Post n'est pas trouvé.
     * @param message
     */
    public PostNotFoundException(String message) {
        super(message);
    }
}
