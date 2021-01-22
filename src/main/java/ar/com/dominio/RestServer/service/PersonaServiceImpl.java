package ar.com.dominio.RestServer.service;

import ar.com.dominio.RestServer.entity.Persona;
import ar.com.dominio.RestServer.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PersonaServiceImpl implements PersonaService {

    private final PersonaRepository personaRepository;

    @Autowired
    public PersonaServiceImpl(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    @Override
    public List<Persona> getAll() {
        return personaRepository.findAll();
    }

    @Override
    public Optional<Persona> getPersonaById(Long id) {
        return personaRepository.findById(id);
    }

    @Override
    public void create(Persona persona) {
        personaRepository.save(persona);
    }

    @Override
    @Transactional
    public void update(Long id, String nombre, String apellido, LocalDate fechaNacimiento) {
        Persona persona = personaRepository.findById(id).orElseThrow(() ->
                new IllegalStateException("No se encontró a la persona para actualizar"));

        if(nombre != null && !nombre.isEmpty())
            persona.setNombre(nombre);
        if(apellido != null && !apellido.isEmpty())
            persona.setApellido(apellido);
        if(fechaNacimiento != null)
            persona.setFechaNacimiento(fechaNacimiento);
    }

    @Override
    public void delete(Long id) {
        boolean exists = personaRepository.existsById(id);
        if(!exists) {
            throw new IllegalStateException("No se encontró a la persona para eliminar");
        }
        personaRepository.deleteById(id);
    }

}
