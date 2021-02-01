package ar.com.dominio.RestServer.service;

import ar.com.dominio.RestServer.entity.Persona;

import java.util.List;

public interface PersonaService {

    List<Persona> getAll();

    Persona getById(Long id);

    void create(Persona persona);

    void update(Long id, Persona personaData);

    void delete(Long id);

}
