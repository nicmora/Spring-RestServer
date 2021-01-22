package ar.com.dominio.RestServer.service;

import ar.com.dominio.RestServer.entity.Persona;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PersonaService {

    List<Persona> getAll();

    Optional<Persona> getPersonaById(Long id);

    void create(Persona persona);

    void update(Long id, String nombre, String apellido, LocalDate fechaNacimiento);

    void delete(Long id);

}
