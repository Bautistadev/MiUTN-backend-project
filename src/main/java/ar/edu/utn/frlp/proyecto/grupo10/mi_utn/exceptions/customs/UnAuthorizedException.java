package ar.edu.utn.frlp.proyecto.grupo10.mi_utn.exceptions.customs;

public class UnAuthorizedException extends RuntimeException{
    public UnAuthorizedException(String message){
        super(message);
    }
}
