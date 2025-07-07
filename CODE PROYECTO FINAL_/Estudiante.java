import java.util.*;

public class Estudiante {
    private String correo;
    private String contrasena;
    private List<Materia> materias;
    private List<Horario> horarios;
    private List<Grupo> grupos;

    private boolean bloqueado;
    private int intentosFallidos;

    public Estudiante(String correo, String contrasena) {
        this.correo = correo;
        this.contrasena = contrasena;
        this.materias = new ArrayList<>();
        this.horarios = new ArrayList<>();
        this.grupos = new ArrayList<>();
        this.bloqueado = false;
        this.intentosFallidos = 0;
    }

    public boolean iniciarSesion(String correo, String contrasena) throws Exception {
        if (bloqueado) {
            throw new Exception("Cuenta bloqueada temporalmente.");
        }
        if (this.correo.equals(correo) && this.contrasena.equals(contrasena)) {
            intentosFallidos = 0;
            return true;
        } else {
            intentosFallidos++;
            if (intentosFallidos >= 5) {
                bloqueado = true;
                throw new Exception("Cuenta bloqueada tras 5 intentos fallidos.");
            }
            return false;
        }
    }

    public void registrarMateria(Materia materia) {
        if(!materias.contains(materia)) {
            materias.add(materia);
        }
    }

    public void agregarHorario(Horario horario) {
        horarios.add(horario);
    }

    public void unirseAGrupo(Grupo grupo) {
        if(!grupos.contains(grupo)) {
            grupos.add(grupo);
            grupo.agregarEstudiante(this);
        }
    }

    public Grupo crearGrupo(String nombreGrupo, Materia materia) {
        Grupo grupo = new Grupo(nombreGrupo, materia);
        grupos.add(grupo);
        grupo.agregarEstudiante(this);
        return grupo;
    }

    public String getCorreo() {
        return correo;
    }

    public List<Materia> getMaterias() {
        return materias;
    }

    public List<Horario> getHorarios() {
        return horarios;
    }

    public List<Grupo> getGrupos() {
        return grupos;
    }
}
