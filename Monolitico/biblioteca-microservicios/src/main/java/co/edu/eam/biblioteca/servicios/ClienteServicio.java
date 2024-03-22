package co.edu.eam.biblioteca.servicios;

import co.edu.eam.biblioteca.dto.ClienteGetDTO;
import co.edu.eam.biblioteca.dto.ClientePostDTO;
import co.edu.eam.biblioteca.entity.Cliente;
import co.edu.eam.biblioteca.repo.ClienteRepo;
import co.edu.eam.biblioteca.servicios.excepciones.ClienteNoEncontradoException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ClienteServicio {

    private final ClienteRepo clienteRepo;

    public ClienteGetDTO save(ClientePostDTO cliente){
        return convertir( clienteRepo.save( convertir(cliente) ) );
    }


    public ClienteGetDTO findById(Long codigoCliente){
        Cliente cliente = obtenerCliente(codigoCliente);
        return convertir(cliente);
    }

    public void delete(long codigoCliente){
        obtenerCliente(codigoCliente);
        clienteRepo.deleteById(codigoCliente);
    }

    public ClienteGetDTO update(long codigoCliente, ClientePostDTO clienteNuevo){
        obtenerCliente(codigoCliente);

        Cliente nuevo = convertir(clienteNuevo);
        nuevo.setCodigo(codigoCliente);
        return convertir( clienteRepo.save(nuevo) );
    }

    public List<ClienteGetDTO> findAll(){
        return clienteRepo.findAll()
                .stream()
                .map(c -> convertir(c))
                .collect(Collectors.toList());
    }

    private Cliente obtenerCliente(Long codigoCliente){
        return clienteRepo.findById(codigoCliente).orElseThrow( () -> new ClienteNoEncontradoException("El cliente no existe") );
    }

    private ClienteGetDTO convertir(Cliente cliente){
        return new ClienteGetDTO(cliente.getCodigo(), cliente.getNombre(), cliente.getEmail(), cliente.getTelefono());
    }

    private Cliente convertir(ClientePostDTO cliente){
        return Cliente.builder()
                .nombre(cliente.nombre())
                .email(cliente.email())
                .telefono(cliente.telefono())
                .password(cliente.password()).build();
    }

}
