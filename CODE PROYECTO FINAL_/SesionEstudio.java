import java.time.LocalDateTime;
import java.util.*;

public class SesionEstudio {
    private Materia materia;
    private String lugar;
    private LocalDateTime fechaHora;
    private List<Estudiante> asistentes;

    public SesionEstudio(Materia materia, String lugar, LocalDateTime fechaHora) {
        this.materia = materia;
        this.lugar = lugar;
        this.fechaHora = fechaHora;
        this.asistentes = new ArrayList<>();
    }

    public void agregarAsistente(Estudiante estudiante) {
        if (!asistentes.contains(estudiante)) {
            asistentes.add(estudiante);
        }
    }

    public Materia getMateria() {
        return materia;
    }

    public String getLugar() {
        return lugar;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public List<Estudiante> getAsistentes() {
        return asistentes;
    }
}
