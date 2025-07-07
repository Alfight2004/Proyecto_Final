import java.util.ArrayList;
import java.util.List;

public class Grupo {
    private String nombre;
    private String materia;
    private List<Usuario> miembros;
    private List<String> chat;
    private List<String> sesiones;

    public Grupo(String nombre, String materia) {
        this.nombre = nombre;
        this.materia = materia;
        this.miembros = new ArrayList<>();
        this.chat = new ArrayList<>();
        this.sesiones = new ArrayList<>();
    }

    public String getNombre() { return nombre; }
    public String getMateria() { return materia; }
    public List<Usuario> getMiembros() { return miembros; }

    public void agregarMiembro(Usuario usuario) {
        miembros.add(usuario);
    }

    public void enviarMensaje(String mensaje) {
        chat.add(mensaje);
    }

    public List<String> getChat() {
        return chat;
    }

    public void crearSesion(String descripcion) {
        sesiones.add(descripcion);
    }

    public List<String> getSesiones() {
        return sesiones;
    }
}

