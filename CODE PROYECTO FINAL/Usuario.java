import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String correo;
    private String contrasena;
    private int intentosFallidos;
    private boolean bloqueado;
    private List<String> materias;
    private List<String> horarios;
    private List<Grupo> grupos;

    public Usuario(String correo, String contrasena) {
        this.correo = correo;
        this.contrasena = contrasena;
        this.intentosFallidos = 0;
        this.bloqueado = false;
        this.materias = new ArrayList<>();
        this.horarios = new ArrayList<>();
        this.grupos = new ArrayList<>();
    }

    public String getCorreo() { return correo; }
    public String getContrasena() { return contrasena; }
    public int getIntentosFallidos() { return intentosFallidos; }
    public boolean isBloqueado() { return bloqueado; }
    public List<String> getMaterias() { return materias; }
    public List<String> getHorarios() { return horarios; }
    public List<Grupo> getGrupos() { return grupos; }

    public void incrementarIntento() { intentosFallidos++; }
    public void resetearIntentos() { intentosFallidos = 0; }
    public void bloquear() { bloqueado = true; }
    public void desbloquear() { bloqueado = false; }
}
