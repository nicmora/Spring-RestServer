package ar.com.dominio.RestServer.controller;

import ar.com.dominio.RestServer.model.Persona;
import ar.com.dominio.RestServer.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public ResponseEntity<List<Persona>> getAll() {
        List<Persona> personas = personaService.getAll();
        return new ResponseEntity<>(personas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Persona> getById(@PathVariable("id") Long id) {
        Persona persona = personaService.getById(id);
        return new ResponseEntity<>(persona, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Persona> create(@Valid @RequestBody Persona personaData) {
        Persona persona = personaService.create(personaData);
        return new ResponseEntity<>(persona, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Persona> update(
            @PathVariable("id") Long id,
            @Valid @RequestBody Persona personaData) {
        Persona persona = personaService.update(id, personaData);
        return new ResponseEntity<>(persona, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Persona> delete(@PathVariable("id") Long id) {
        personaService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
