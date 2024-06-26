package co.edu.eam.biblioteca.controller;

import co.edu.eam.biblioteca.dto.Respuesta;
import co.edu.eam.biblioteca.service.excepciones.PrestamoNoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ManejoExcepciones {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Respuesta<String>> capturarException(Exception e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body( new Respuesta<>(e.getMessage()) );
    }

    @ExceptionHandler(PrestamoNoEncontradoException.class)
    public ResponseEntity<Respuesta<String>> capturarPrestamoNoEncontradoException(PrestamoNoEncontradoException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body( new Respuesta<>(e.getMessage()) );
    }

}
