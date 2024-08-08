package com.openclassrooms.mddapi.Services.Interfaces;

import io.jsonwebtoken.Claims;
import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;
import org.springframework.security.core.userdetails.UserDetails;


/**
 * Interface Service pour la gestion des tokens JWT.
 */

public interface JwtService {

    /**
     * Extrait le nom d'utilisateur du token JWT.
     * @param token Le token JWT à partir duquel extraire le nom d'utilisateur.
     * @return Le nom d'utilisateur extrait du token JWT.
     */
    String extractUsername(String token);
    /**
     * Extrait une revendication spécifique du token JWT en utilisant un résolveur de revendications.
     * @param token Le token JWT à partir duquel extraire la revendication.
     * @param claimsResolver Le résolveur de revendications pour extraire la revendication souhaitée.
     * @param <T> Le type de la revendication à extraire.
     * @return La revendication extraite du token JWT.
     */
     <T> T extractClaim(String token, Function<Claims, T> claimsResolver);

    /**
     * Génère un token JWT pour un utilisateur donné.
     * @param userDetails Les détails de l'utilisateur pour lesquels générer le token JWT.
     * @return Le token JWT généré.
     */
    String generateToken(UserDetails userDetails);
    /**
     * Génère un token JWT avec des revendications supplémentaires pour un utilisateur donné.
     * @param extraClaims Les revendications supplémentaires à inclure dans le token JWT.
     * @param userDetails Les détails de l'utilisateur pour lesquels générer le token JWT.
     * @return Le token JWT généré.
     */
    String generateToken(Map<String, Object> extraClaims, UserDetails userDetails);

    /**
     * Renvoie le temps d'expiration des tokens JWT.
     * @return Le temps d'expiration des tokens JWT.
     */
    long getExpirationTime();

    /**
     * Construit un token JWT avec les revendications et le temps d'expiration spécifiés.
     * @param extraClaims Les revendications supplémentaires à inclure dans le token JWT.
     * @param userDetails Les détails de l'utilisateur pour lesquels générer le token JWT.
     * @param expiration Le temps d'expiration du token JWT.
     * @return Le token JWT construit.
     */
    String buildToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails,
            long expiration
    );
    /**
     * Vérifie si un token JWT est valide pour un utilisateur donné.
     * @param token Le token JWT à valider.
     * @param userDetails Les détails de l'utilisateur pour lesquels valider le token JWT.
     * @return true si le token JWT est valide, sinon false.
     */
    boolean isTokenValid(String token, UserDetails userDetails);

    /**
     * Vérifie si un token JWT a expiré.
     * @param token Le token JWT à vérifier.
     * @return true si le token JWT a expiré, sinon false.
     */
    boolean isTokenExpired(String token);

    /**
     * Extrait la date d'expiration d'un token JWT.
     * @param token Le token JWT à partir duquel extraire la date d'expiration.
     * @return La date d'expiration extraite du token JWT.
     */
    Date extractExpiration(String token);

    /**
     * Extrait toutes les revendications d'un token JWT.
     * @param token Le token JWT à partir duquel extraire toutes les revendications.
     * @return Toutes les revendications extraites du token JWT.
     */
     Claims extractAllClaims(String token);

    /**
     * Récupère la clé de signature à partir de la clé secrète spécifiée.
     * @return La clé de signature extraite de la clé secrète.
     */
     Key getSignInKey();
}


