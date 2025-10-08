package ar.edu.utn.frlp.proyecto.grupo10.mi_utn.exceptions.customs;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class BadRequestException extends Exception{
    public BadRequestException(String message){
        super(message);
    }
}
