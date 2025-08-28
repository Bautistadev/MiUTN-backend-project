package ar.edu.utn.frlp.proyecto.grupo10.mi_utn.exceptions.customs;

public class BadRequestException extends RuntimeException{
    public BadRequestException(String message){
        super(message);
    }
}
