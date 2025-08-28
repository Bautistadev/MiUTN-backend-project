package ar.edu.utn.frlp.proyecto.grupo10.mi_utn.exceptions;

import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.exceptions.customs.BadRequestException;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.exceptions.customs.ConflictException;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.exceptions.customs.UnAuthorizedException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ExceptionManager {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorMessageDTO> BadRequestException(Exception e, HttpServletRequest request){
        ErrorMessageDTO error = ErrorMessageDTO.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .message(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .path(request.getContextPath())
                .timeStamp(LocalDateTime.now())
                .message(e.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(error);
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ErrorMessageDTO> ConflictException(Exception e, HttpServletRequest request){
        ErrorMessageDTO error = ErrorMessageDTO.builder()
                .code(HttpStatus.CONFLICT.value())
                .message(HttpStatus.CONFLICT.getReasonPhrase())
                .path(request.getContextPath())
                .timeStamp(LocalDateTime.now())
                .message(e.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .contentType(MediaType.APPLICATION_JSON)
                .body(error);
    }


    @ExceptionHandler(UnAuthorizedException.class)
    public ResponseEntity<ErrorMessageDTO> UnAuthorizedException(Exception e, HttpServletRequest request){
        ErrorMessageDTO error = ErrorMessageDTO.builder()
                .code(HttpStatus.UNAUTHORIZED.value())
                .message(HttpStatus.UNAUTHORIZED.getReasonPhrase())
                .path(request.getContextPath())
                .timeStamp(LocalDateTime.now())
                .message(e.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(error);
    }



    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessageDTO> Exception(Exception e, HttpServletRequest request){
        ErrorMessageDTO error = ErrorMessageDTO.builder()
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .path(request.getContextPath())
                .timeStamp(LocalDateTime.now())
                .message(e.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(MediaType.APPLICATION_JSON)
                .body(error);
    }
}
