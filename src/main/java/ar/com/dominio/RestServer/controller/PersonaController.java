package ar.com.dominio.RestServer.controller;

import ar.com.dominio.RestServer.entity.Persona;
import ar.com.dominio.RestServer.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/persona")
public class PersonaController {

    private final PersonaService personaService;

    @Autowired
    public PersonaController(PersonaService personaService) {
        this.personaService = personaService;
    }

    @GetMapping
    public List<Persona> getAll() {
        return personaService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Persona> getPersonaById(@PathVariable("id") Long id) {
        return personaService.getPersonaById(id);
    }

    @PostMapping
    public void create(@RequestBody Persona persona) {
        personaService.create(persona);
    }

    @PutMapping("/{id}")
    public void update(
            @PathVariable("id") Long id,
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String apellido,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate fechaNacimiento) {
        personaService.update(id, nombre, apellido, fechaNacimiento);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        personaService.delete(id);
    }

}
