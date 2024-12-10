/*Création du package exception.
Ajout de la classe "ResourceNotFoundException" dans ce package qui hérite de la classe "RunTimeException" */

package org.myblognew.MyBlogNew.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}