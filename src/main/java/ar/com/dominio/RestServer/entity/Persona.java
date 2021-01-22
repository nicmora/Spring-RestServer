package ar.com.dominio.RestServer.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;

@Entity
@Table
@Data
public class Persona {

    @Id
    @SequenceGenerator(
            name = "persona_sequence",
            sequenceName = "persona_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "persona_sequence",
            strategy = GenerationType.SEQUENCE
    )
    private Long id;

    private String nombre;

    private String apellido;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate fechaNacimiento;

    @Transient
    private Integer edad;

    public Integer getEdad() {
        return Period.between(fechaNacimiento, LocalDate.now()).getYears();
    }

}
