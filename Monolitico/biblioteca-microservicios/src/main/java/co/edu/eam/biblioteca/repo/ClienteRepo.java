package co.edu.eam.biblioteca.repo;

import co.edu.eam.biblioteca.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepo extends JpaRepository<Cliente, Long> {

    Optional<Cliente> findByEmail(String email);

}
