package ar.com.dominio.RestServer.repository;

import ar.com.dominio.RestServer.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonaRepository extends JpaRepository<Persona, Long> {

    Optional<Persona> findByApellido(String apellido);

}
