package co.edu.eam.biblioteca.servicios.excepciones;

public class ClienteNoEncontradoException extends RuntimeException{

    public ClienteNoEncontradoException(String mensaje){
        super(mensaje);
    }

}
