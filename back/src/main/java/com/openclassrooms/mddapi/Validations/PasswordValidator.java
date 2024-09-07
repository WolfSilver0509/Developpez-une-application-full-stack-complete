package com.openclassrooms.mddapi.Validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * PasswordValidator class
 * Cette classe permet de valider un mot de passe
 */
public class PasswordValidator implements ConstraintValidator<ValidPassword, String> {

    /**
     * Méthode d'initialisation de la contrainte
     * @param constraintAnnotation
     */
    @Override
    public void initialize(ValidPassword constraintAnnotation) {
    }

    /**
     * Méthode de validation du mot de passe avec regex
     * @param password
     * @param context
     * @return boolean
     */
    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        if (password == null || password.length() < 8) {
            return false;
        }
        boolean hasDigit = false;
        boolean hasLower = false;
        boolean hasUpper = false;
        boolean hasSpecial = false;

        for (char c : password.toCharArray()) {
            if (Character.isDigit(c)) {
                hasDigit = true;
            } else if (Character.isLowerCase(c)) {
                hasLower = true;
            } else if (Character.isUpperCase(c)) {
                hasUpper = true;
            } else if (!Character.isLetterOrDigit(c)) {
                hasSpecial = true;
            }
        }
        return hasDigit && hasLower && hasUpper && hasSpecial;
    }
}