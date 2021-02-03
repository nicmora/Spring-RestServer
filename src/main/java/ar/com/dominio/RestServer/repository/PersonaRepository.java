package ar.com.dominio.RestServer.repository;

import ar.com.dominio.RestServer.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<Persona, Long> {

    boolean existsByDni(Long dni);

}
