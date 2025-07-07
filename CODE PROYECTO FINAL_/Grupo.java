import java.util.*;

public class Grupo {
    private String nombre;
    private Materia materia;
    private List<Estudiante> estudiantes;
    private List<Mensaje> mensajes;
    private List<SesionEstudio> sesiones;

    public Grupo(String nombre, Materia materia) {
        this.nombre = nombre;
        this.materia = materia;
        this.estudiantes = new ArrayList<>();
        this.mensajes = new ArrayList<>();
        this.sesiones = new ArrayList<>();
    }

    public void agregarEstudiante(Estudiante estudiante) {
        if(!estudiantes.contains(estudiante)) {
            estudiantes.add(estudiante);
        }
    }

    public void enviarMensaje(Mensaje mensaje) {
        mensajes.add(mensaje);
    }

    public void agregarSesion(SesionEstudio sesion) {
        sesiones.add(sesion);
    }

    public String getNombre() {
        return nombre;
    }

    public Materia getMateria() {
        return materia;
    }

    public List<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public List<Mensaje> getMensajes() {
        return mensajes;
    }

    public List<SesionEstudio> getSesiones() {
        return sesiones;
    }
}
