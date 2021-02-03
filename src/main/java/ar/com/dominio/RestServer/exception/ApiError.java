package ar.com.dominio.RestServer.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiError {

    private String error;
    private String message;
    private String path;
    private int status;
    private LocalDateTime timestamp;

}
