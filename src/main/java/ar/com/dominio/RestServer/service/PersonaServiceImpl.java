package ar.com.dominio.RestServer.service;

import ar.com.dominio.RestServer.model.Persona;
import ar.com.dominio.RestServer.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PersonaServiceImpl implements PersonaService {

    private final PersonaRepository personaRepository;

    @Autowired
    public PersonaServiceImpl(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    @Override
    public List<Persona> getAll() {
        List<Persona> personas = personaRepository.findAll();
        if (personas.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existen datos en la base.");
        }
        return personas;
    }

    @Override
    public Persona getById(Long id) {
        return personaRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe la persona con id: " + id));
    }

    @Override
    public Persona create(Persona personaData) {
        return personaRepository.save(personaData);
    }

    @Override
    @Transactional
    public Persona update(Long id, Persona personaData) {
        Persona persona = personaRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró a la persona para actualizar."));
        if (!persona.getDni().equals(personaData.getDni())) {
            boolean exists = personaRepository.existsByDni(personaData.getDni());
            if (exists) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ya existe una persona con el dni: " + personaData.getDni());
            }
        }
        persona.setNombre(personaData.getNombre());
        persona.setApellido(personaData.getApellido());
        persona.setFechaNacimiento(personaData.getFechaNacimiento());
        persona.setDni(personaData.getDni());
        return persona;
    }

    @Override
    public void delete(Long id) {
        boolean exists = personaRepository.existsById(id);
        if (!exists) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró a la persona para eliminar.");
        }
        personaRepository.deleteById(id);
    }

}
