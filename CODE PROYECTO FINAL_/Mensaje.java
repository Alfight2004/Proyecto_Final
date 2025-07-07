import java.time.LocalDateTime;

public class Mensaje {
    private Estudiante remitente;
    private String contenido;
    private LocalDateTime fechaHora;

    public Mensaje(Estudiante remitente, String contenido) {
        this.remitente = remitente;
        this.contenido = contenido;
        this.fechaHora = LocalDateTime.now();
    }

    public Estudiante getRemitente() {
        return remitente;
    }

    public String getContenido() {
        return contenido;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }
}
