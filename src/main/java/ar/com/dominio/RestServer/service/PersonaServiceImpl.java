package ar.com.dominio.RestServer.service;

import ar.com.dominio.RestServer.entity.Persona;
import ar.com.dominio.RestServer.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
            throw new IllegalStateException("No existen datos.");
        }
        return personas;
    }

    @Override
    public Persona getById(Long id) {
        return personaRepository.findById(id).orElseThrow(() -> new IllegalStateException("No existe la persona con id: " + id));
    }

    @Override
    public void create(Persona persona) {
        personaRepository.save(persona);
    }

    @Override
    @Transactional
    public void update(Long id, Persona personaData) {
        Persona persona = personaRepository.findById(id).orElseThrow(() ->
                new IllegalStateException("No se encontró a la persona para actualizar"));
        persona.setNombre(personaData.getNombre());
        persona.setApellido(personaData.getApellido());
        persona.setFechaNacimiento(personaData.getFechaNacimiento());
        persona.setDni(personaData.getDni());
    }

    @Override
    public void delete(Long id) {
        boolean exists = personaRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("No se encontró a la persona para eliminar");
        }
        personaRepository.deleteById(id);
    }

}
