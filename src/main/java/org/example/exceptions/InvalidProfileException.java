package org.example.exceptions;

public class InvalidProfileException extends RuntimeException{
     private String message;

     public InvalidProfileException(String message){
         super(message);
     }


}
