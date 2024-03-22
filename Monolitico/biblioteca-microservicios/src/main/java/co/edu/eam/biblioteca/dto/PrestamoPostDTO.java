package co.edu.eam.biblioteca.dto;

import java.time.LocalDateTime;
import java.util.List;

public record PrestamoPostDTO(long clienteID, List<String> isbnLibros, LocalDateTime fechaDevolucion) {
}
