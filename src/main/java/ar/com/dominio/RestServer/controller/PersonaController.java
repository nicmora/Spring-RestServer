package ar.com.dominio.RestServer.controller;

import ar.com.dominio.RestServer.entity.Persona;
import ar.com.dominio.RestServer.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/persona")
@CrossOrigin(origins = "http://localhost:4200")
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
    public Persona getById(@PathVariable("id") Long id) {
        return personaService.getById(id);
    }

    @PostMapping
    public void create(@RequestBody Persona persona) {
        personaService.create(persona);
    }

    @PutMapping("/{id}")
    public void update(
            @PathVariable("id") Long id,
            @RequestBody Persona personaData) {
        personaService.update(id, personaData);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        personaService.delete(id);
    }

}
