package co.edu.eam.biblioteca.servicios.excepciones;

public class LibroNoEncontradoException extends RuntimeException {

    public LibroNoEncontradoException(String message) {
        super(message);
    }
}
