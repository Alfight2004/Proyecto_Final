import java.util.*;

public class Sistema {
    private Map<String, Usuario> usuarios = new HashMap<>();
    private List<Grupo> grupos = new ArrayList<>();

    public String registrarUsuario(String correo, String contrasena) {
        if (usuarios.containsKey(correo)) return "Correo ya registrado.";
        usuarios.put(correo, new Usuario(correo, contrasena));
        return "Usuario registrado.";
    }

    public String iniciarSesion(String correo, String contrasena) {
        Usuario usuario = usuarios.get(correo);
        if (usuario == null) return "Correo no registrado.";
        if (usuario.isBloqueado()) return "Cuenta bloqueada.";

        if (usuario.getContrasena().equals(contrasena)) {
            usuario.resetearIntentos();
            return "Acceso concedido.";
        } else {
            usuario.incrementarIntento();
            if (usuario.getIntentosFallidos() >= 5) {
                usuario.bloquear();
                return "Cuenta bloqueada por múltiples intentos.";
            }
            return "Contraseña incorrecta.";
        }
    }

    public void registrarMaterias(String correo, List<String> materias) {
        Usuario u = usuarios.get(correo);
        u.getMaterias().addAll(materias);
    }

    public void configurarHorarios(String correo, List<String> horarios) {
        Usuario u = usuarios.get(correo);
        u.getHorarios().clear();
        u.getHorarios().addAll(horarios);
    }

    public List<String> sugerirGrupos(String correo) {
        Usuario u = usuarios.get(correo);
        List<String> sugerencias = new ArrayList<>();
        for (Grupo g : grupos) {
            boolean mismaMateria = u.getMaterias().stream().anyMatch(m -> m.equalsIgnoreCase(g.getMateria()));
            boolean horarioCoincide = !Collections.disjoint(u.getHorarios(), g.getMiembros().get(0).getHorarios());
            if (mismaMateria && horarioCoincide) {
                sugerencias.add(g.getNombre());
            }
        }
        return sugerencias;
    }

    public String crearGrupo(String correo, String nombreGrupo, String materia) {
        Usuario u = usuarios.get(correo);
        Grupo grupo = new Grupo(nombreGrupo, materia);
        grupo.agregarMiembro(u);
        u.getGrupos().add(grupo);
        grupos.add(grupo);
        return "Grupo creado.";
    }

    public String unirseAGrupo(String correo, String nombreGrupo) {
        Usuario u = usuarios.get(correo);
        for (Grupo g : grupos) {
            if (g.getNombre().equalsIgnoreCase(nombreGrupo)) {
                g.agregarMiembro(u);
                u.getGrupos().add(g);
                return "Unido al grupo.";
            }
        }
        return "Grupo no encontrado.";
    }

    public void enviarMensaje(String correo, String nombreGrupo, String mensaje) {
        for (Grupo g : grupos) {
            if (g.getNombre().equalsIgnoreCase(nombreGrupo)) {
                g.enviarMensaje(correo + ": " + mensaje);
            }
        }
    }

    public List<String> verChat(String nombreGrupo) {
        for (Grupo g : grupos) {
            if (g.getNombre().equalsIgnoreCase(nombreGrupo)) {
                return g.getChat();
            }
        }
        return List.of("Grupo no encontrado.");
    }

    public void crearSesion(String correo, String nombreGrupo, String descripcion) {
        for (Grupo g : grupos) {
            if (g.getNombre().equalsIgnoreCase(nombreGrupo)) {
                g.crearSesion("Sesión creada por " + correo + ": " + descripcion);
            }
        }
    }

    public List<String> verSesiones(String nombreGrupo) {
        for (Grupo g : grupos) {
            if (g.getNombre().equalsIgnoreCase(nombreGrupo)) {
                return g.getSesiones();
            }
        }
        return List.of("Grupo no encontrado.");
    }
}
