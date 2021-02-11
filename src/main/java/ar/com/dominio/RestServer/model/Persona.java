package ar.com.dominio.RestServer.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.Period;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
    @Column(nullable = false, updatable = false)
    private Long id;

    private String nombre;

    private String apellido;

    @NotNull(message = "El dni es obligatoria")
    @Column(nullable = false, unique = true)
    private Long dni;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate fechaNacimiento;

    @Transient
    private Integer edad;

    public Integer getEdad() {
        return Period.between(fechaNacimiento, LocalDate.now()).getYears();
    }

}
