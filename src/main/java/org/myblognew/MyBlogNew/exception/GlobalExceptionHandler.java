package org.myblognew.MyBlogNew.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFound(ResourceNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    /*L'annotation @ExceptionHandler indique à Spring que cette méthode doit être exécutée lorsque l'application rencontre
    une exception du type précisé en paramètre, ici ResourceNotFoundException.

    La méthode handleResourceNotFound retourne une ResponseEntity avec un code status 404 ainsi qu'un message qui sera
    défini lors de la levée de l'exception.*/

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGlobalException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Une erreur est survenue");
    }
    /* Pour gérer toutes les autres exceptions non spécifiées dans d'autres méthodes @ExceptionHandler,
    ajouter une méthode handleGlobalException à la classe GlobalExceptionHandler :
    Si une exception inattendue survient dans l'application, cette méthode garantit qu'une réponse appropriée est envoyée au client,
    même si l'erreur spécifique n'a pas été prévue. */

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
