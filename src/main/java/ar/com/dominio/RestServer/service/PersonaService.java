package ar.com.dominio.RestServer.service;

import ar.com.dominio.RestServer.model.Persona;

import java.util.List;

public interface PersonaService {

    List<Persona> getAll();

    Persona getById(Long id);

    Persona create(Persona personaData);

    Persona update(Long id, Persona personaData);

    void delete(Long id);

}
