import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static Map<String, Estudiante> estudiantes = new HashMap<>();
    static List<Materia> materiasDisponibles = new ArrayList<>();
    static List<Grupo> grupos = new ArrayList<>();

    public static void main(String[] args) {
        cargarMateriasIniciales();

        while(true) {
            System.out.println("\n--- Plataforma Académica ---");
            System.out.println("1. Registrar estudiante");
            System.out.println("2. Iniciar sesión");
            System.out.println("3. Salir");
            System.out.print("Opción: ");
            String opcion = sc.nextLine();

            switch (opcion) {
                case "1":
                    registrarEstudiante();
                    break;
                case "2":
                    login();
                    break;
                case "3":
                    System.out.println("Saliendo...");
                    return;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    static void cargarMateriasIniciales() {
        materiasDisponibles.add(new Materia("Matemáticas"));
        materiasDisponibles.add(new Materia("Programación"));
        materiasDisponibles.add(new Materia("Física"));
        materiasDisponibles.add(new Materia("Química"));
        materiasDisponibles.add(new Materia("Historia"));
    }

    static void registrarEstudiante() {
        System.out.print("Correo: ");
        String correo = sc.nextLine();
        if (estudiantes.containsKey(correo)) {
            System.out.println("Correo ya registrado.");
            return;
        }
        System.out.print("Contraseña: ");
        String contrasena = sc.nextLine();
        estudiantes.put(correo, new Estudiante(correo, contrasena));
        System.out.println("Estudiante registrado.");
    }

    static void login() {
        System.out.print("Correo: ");
        String correo = sc.nextLine();
        System.out.print("Contraseña: ");
        String contrasena = sc.nextLine();

        Estudiante est = estudiantes.get(correo);
        if(est == null) {
            System.out.println("Usuario no encontrado.");
            return;
        }

        try {
            if(est.iniciarSesion(correo, contrasena)) {
                System.out.println("Bienvenido, " + correo);
                menuUsuario(est);
            } else {
                System.out.println("Contraseña incorrecta.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    static void menuUsuario(Estudiante est) {
        while(true) {
            System.out.println("\n--- Menú de usuario ---");
            System.out.println("1. Registrar materias");
            System.out.println("2. Configurar horarios");
            System.out.println("3. Ver sugerencias de grupos");
            System.out.println("4. Crear grupo");
            System.out.println("5. Unirse a grupo");
            System.out.println("6. Ver mis grupos");
            System.out.println("7. Enviar mensaje a grupo");
            System.out.println("8. Crear sesión de estudio");
            System.out.println("9. Ver sesiones de grupo");
            System.out.println("0. Cerrar sesión");
            System.out.print("Opción: ");
            String op = sc.nextLine();

            switch(op) {
                case "1":
                    registrarMateriasUsuario(est);
                    break;
                case "2":
                    configurarHorariosUsuario(est);
                    break;
                case "3":
                    mostrarSugerencias(est);
                    break;
                case "4":
                    crearGrupoUsuario(est);
                    break;
                case "5":
                    unirseAGrupoUsuario(est);
                    break;
                case "6":
                    mostrarGrupos(est);
                    break;
                case "7":
                    enviarMensajeUsuario(est);
                    break;
                case "8":
                    crearSesionUsuario(est);
                    break;
                case "9":
                    verSesionesUsuario(est);
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    static void registrarMateriasUsuario(Estudiante est) {
        System.out.println("Materias disponibles:");
        for (int i = 0; i < materiasDisponibles.size(); i++) {
            System.out.println((i+1) + ". " + materiasDisponibles.get(i).getNombre());
        }
        System.out.print("Elija número de materia para registrar (0 para salir): ");
        while(true) {
            String input = sc.nextLine();
            if(input.equals("0")) break;
            try {
                int idx = Integer.parseInt(input) - 1;
                if(idx >= 0 && idx < materiasDisponibles.size()) {
                    est.registrarMateria(materiasDisponibles.get(idx));
                    System.out.println("Materia registrada: " + materiasDisponibles.get(idx).getNombre());
                } else {
                    System.out.println("Número inválido");
                }
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un número válido");
            }
            System.out.print("Registrar otra materia (0 para salir): ");
        }
    }

    static void configurarHorariosUsuario(Estudiante est) {
        System.out.println("Configurar horarios");
        System.out.print("Ingrese día (Ej: Lunes): ");
        String dia = sc.nextLine();
        System.out.print("Hora inicio (HH:mm): ");
        String inicio = sc.nextLine();
        System.out.print("Hora fin (HH:mm): ");
        String fin = sc.nextLine();

        est.agregarHorario(new Horario(dia, inicio, fin));
        System.out.println("Horario agregado.");
    }

    static void mostrarSugerencias(Estudiante est) {
        System.out.println("Sugerencias de grupos según tus materias:");

        Set<Grupo> sugeridos = new HashSet<>();
        for(Grupo g : grupos) {
            for(Materia m : est.getMaterias()) {
                if(g.getMateria().equals(m)) {
                    sugeridos.add(g);
                }
            }
        }

        if(sugeridos.isEmpty()) {
            System.out.println("No hay grupos sugeridos.");
        } else {
            int i=1;
            for(Grupo g : sugeridos) {
                System.out.println(i + ". " + g.getNombre() + " - Materia: " + g.getMateria().getNombre());
                i++;
            }
        }
    }

    static void crearGrupoUsuario(Estudiante est) {
        System.out.print("Nombre del grupo: ");
        String nombreGrupo = sc.nextLine();
        System.out.println("Elija materia para el grupo:");
        for (int i = 0; i < est.getMaterias().size(); i++) {
            System.out.println((i+1) + ". " + est.getMaterias().get(i).getNombre());
        }
        System.out.print("Número de materia: ");
        String input = sc.nextLine();
        try {
            int idx = Integer.parseInt(input) - 1;
            if(idx >= 0 && idx < est.getMaterias().size()) {
                Grupo g = est.crearGrupo(nombreGrupo, est.getMaterias().get(idx));
                grupos.add(g);
                System.out.println("Grupo creado.");
            } else {
                System.out.println("Número inválido.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Número inválido.");
        }
    }

    static void unirseAGrupoUsuario(Estudiante est) {
        if(grupos.isEmpty()) {
            System.out.println("No hay grupos creados.");
            return;
        }
        System.out.println("Grupos disponibles:");
        for (int i = 0; i < grupos.size(); i++) {
            System.out.println((i+1) + ". " + grupos.get(i).getNombre() + " - Materia: " + grupos.get(i).getMateria().getNombre());
        }
        System.out.print("Número de grupo para unirse: ");
        String input = sc.nextLine();
        try {
            int idx = Integer.parseInt(input) - 1;
            if(idx >= 0 && idx < grupos.size()) {
                Grupo g = grupos.get(idx);
                est.unirseAGrupo(g);
                System.out.println("Te uniste al grupo " + g.getNombre());
            } else {
                System.out.println("Número inválido.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Número inválido.");
        }
    }

    static void mostrarGrupos(Estudiante est) {
        List<Grupo> misGrupos = est.getGrupos();
        if(misGrupos.isEmpty()) {
            System.out.println("No perteneces a ningún grupo.");
        } else {
            System.out.println("Tus grupos:");
            int i=1;
            for(Grupo g : misGrupos) {
                System.out.println(i + ". " + g.getNombre() + " - Materia: " + g.getMateria().getNombre());
                i++;
            }
        }
    }

    static void enviarMensajeUsuario(Estudiante est) {
        List<Grupo> misGrupos = est.getGrupos();
        if(misGrupos.isEmpty()) {
            System.out.println("No perteneces a ningún grupo.");
            return;
        }
        System.out.println("Elige grupo para enviar mensaje:");
        for (int i = 0; i < misGrupos.size(); i++) {
            System.out.println((i+1) + ". " + misGrupos.get(i).getNombre());
        }
        System.out.print("Número: ");
        String input = sc.nextLine();
        try {
            int idx = Integer.parseInt(input) -1;
            if(idx >= 0 && idx < misGrupos.size()) {
                Grupo grupo = misGrupos.get(idx);
                System.out.print("Mensaje: ");
                String texto = sc.nextLine();
                Mensaje msg = new Mensaje(est, texto);
                grupo.enviarMensaje(msg);
                System.out.println("Mensaje enviado.");
            } else {
                System.out.println("Número inválido.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Número inválido.");
        }
    }

    static void crearSesionUsuario(Estudiante est) {
        List<Grupo> misGrupos = est.getGrupos();
        if(misGrupos.isEmpty()) {
            System.out.println("No perteneces a ningún grupo.");
            return;
        }
        System.out.println("Elige grupo para crear sesión:");
        for (int i = 0; i < misGrupos.size(); i++) {
            System.out.println((i+1) + ". " + misGrupos.get(i).getNombre());
        }
        System.out.print("Número: ");
        String input = sc.nextLine();
        try {
            int idx = Integer.parseInt(input) -1;
            if(idx >= 0 && idx < misGrupos.size()) {
                Grupo grupo = misGrupos.get(idx);

                System.out.print("Lugar: ");
                String lugar = sc.nextLine();

                System.out.print("Fecha y hora (yyyy-MM-dd HH:mm): ");
                String fechaHoraStr = sc.nextLine();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                LocalDateTime fechaHora = LocalDateTime.parse(fechaHoraStr, formatter);

                SesionEstudio sesion = new SesionEstudio(grupo.getMateria(), lugar, fechaHora);
                sesion.agregarAsistente(est);
                grupo.agregarSesion(sesion);

                System.out.println("Sesión creada.");
            } else {
                System.out.println("Número inválido.");
            }
        } catch (Exception e) {
            System.out.println("Formato de fecha/hora inválido.");
        }
    }

    static void verSesionesUsuario(Estudiante est) {
        List<Grupo> misGrupos = est.getGrupos();
        if(misGrupos.isEmpty()) {
            System.out.println("No perteneces a ningún grupo.");
            return;
        }
        for(Grupo grupo : misGrupos) {
            System.out.println("Sesiones del grupo: " + grupo.getNombre());
            List<SesionEstudio> sesiones = grupo.getSesiones();
            if(sesiones.isEmpty()) {
                System.out.println("  No hay sesiones programadas.");
            } else {
                for(SesionEstudio sesion : sesiones) {
                    System.out.println("  Lugar: " + sesion.getLugar());
                    System.out.println("  Fecha y hora: " + sesion.getFechaHora());
                    System.out.print("  Asistentes: ");
                    for(Estudiante e : sesion.getAsistentes()) {
                        System.out.print(e.getCorreo() + " ");
                    }
                    System.out.println();
                }
            }
        }
    }
}
