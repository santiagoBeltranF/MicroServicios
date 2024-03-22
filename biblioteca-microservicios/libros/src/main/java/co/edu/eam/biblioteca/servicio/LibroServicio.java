package co.edu.eam.biblioteca.servicio;

import co.edu.eam.biblioteca.dto.LibroDTO;
import co.edu.eam.biblioteca.model.Autor;
import co.edu.eam.biblioteca.model.Libro;
import co.edu.eam.biblioteca.repo.AutorRepo;
import co.edu.eam.biblioteca.repo.LibroRepo;
import co.edu.eam.biblioteca.servicio.excepciones.AutorNoEncontradoException;
import co.edu.eam.biblioteca.servicio.excepciones.LibroNoEncontradoException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LibroServicio {

    private final LibroRepo libroRepo;
    private final AutorRepo autorRepo;

    public Libro save(LibroDTO libro){

        Optional<Libro> guardado = libroRepo.findById(libro.isbn());

        if(guardado.isPresent()){
            throw new RuntimeException("El libro con el isbn "+libro.isbn()+" ya existe");
        }

        return libroRepo.save( convertir(libro) );
    }

    public Libro findById(String isbn){
        return libroRepo.findById(isbn).orElseThrow(()-> new LibroNoEncontradoException("El libro no existe"));
    }

    public List<Libro> findAll(){
        return libroRepo.findAll();
    }

    public Libro update(LibroDTO libro){
        return libroRepo.save( convertir(libro) );
    }

    private Libro convertir(LibroDTO libro){

        List<Autor> autores = autorRepo.findAllById( libro.idAutores() );

        if(autores.size()!=libro.idAutores().size()){

            List<Long> idsExistentes = autores.stream().map(Autor::getId).toList();

            String noEncontrados = libro.idAutores()
                    .stream()
                    .filter(id -> !idsExistentes.contains(id))
                    .map(Object::toString)
                    .collect(Collectors.joining(","));

            throw new AutorNoEncontradoException("Los autores "+noEncontrados+" no existen");

        }

        Libro nuevo = Libro.builder()
                .isbn(libro.isbn())
                .nombre(libro.nombre())
                .genero(libro.genero())
                .fechaPublicacion(libro.fechaPublicacion())
                .unidades(libro.unidades())
                .autor(autores)
                .build();

        return nuevo;
    }

}
