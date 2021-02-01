package ar.com.dominio.RestServer.config;

import ar.com.dominio.RestServer.entity.Persona;
import ar.com.dominio.RestServer.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.time.LocalDate;

@Configuration
public class PostgresConfig {

    @Autowired
    private PersonaRepository personaRepository;

    @PostConstruct
    public void loadData() {
        Persona personaOne = Persona.builder().nombre("Pedro").apellido("Gonzalez").fechaNacimiento(LocalDate.parse("1990-01-10")).dni(30303303L).build();
        Persona personaTwo = Persona.builder().nombre("Pablo").apellido("Rodriguez").fechaNacimiento(LocalDate.parse("1995-05-15")).dni(40404404L).build();
        personaRepository.save(personaOne);
        personaRepository.save(personaTwo);
    }

    @PreDestroy
    public void removeData() {
        personaRepository.deleteAll();
    }

}
