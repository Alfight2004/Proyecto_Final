import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Sistema sistema = new Sistema();

        System.out.println("=== Registro de usuario ===");
        System.out.print("Correo: ");
        String correo = sc.nextLine().toLowerCase();
        System.out.print("Contraseña: ");
        String contrasena = sc.nextLine();
        sistema.registrarUsuario(correo, contrasena);

        System.out.println("\n=== Iniciar sesión ===");
        boolean acceso = false;
        while (!acceso) {
            System.out.print("Correo: ");
            String c = sc.nextLine().toLowerCase();
            System.out.print("Contraseña: ");
            String p = sc.nextLine();
            String resultado = sistema.iniciarSesion(c, p);
            System.out.println(resultado);
            if (resultado.equals("Acceso concedido.")) {
                acceso = true;
                correo = c;
            } else if (resultado.contains("bloqueada")) {
                return;
            }
        }

        int opcion = 1;
        while (opcion != 0) {
            System.out.println("\n=== Menú Principal ===");
            System.out.println("1. Registrar materias");
            System.out.println("2. Configurar horarios");
            System.out.println("3. Crear grupo");
            System.out.println("4. Unirse a grupo");
            System.out.println("5. Ver sugerencias de grupo");
            System.out.println("6. Enviar mensaje al grupo");
            System.out.println("7. Ver chat del grupo");
            System.out.println("8. Crear sesión de estudio");
            System.out.println("9. Ver sesiones de estudio");
            System.out.println("0. Salir");
            System.out.print("Elige una opción: ");
            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1 -> {
                    System.out.print("Ingrese materias separadas por coma: ");
                    List<String> materias = Arrays.asList(sc.nextLine().split(","));
                    sistema.registrarMaterias(correo, materias);
                }
                case 2 -> {
                    System.out.print("Ingrese horarios separados por coma: ");
                    List<String> horarios = Arrays.asList(sc.nextLine().split(","));
                    sistema.configurarHorarios(correo, horarios);
                }
                case 3 -> {
                    System.out.print("Nombre del grupo: ");
                    String nombre = sc.nextLine();
                    System.out.print("Materia: ");
                    String materia = sc.nextLine();
                    System.out.println(sistema.crearGrupo(correo, nombre, materia));
                }
                case 4 -> {
                    System.out.print("Nombre del grupo: ");
                    String nombre = sc.nextLine();
                    System.out.println(sistema.unirseAGrupo(correo, nombre));
                }
                case 5 -> {
                    System.out.println("Sugerencias:");
                    sistema.sugerirGrupos(correo).forEach(System.out::println);
                }
                case 6 -> {
                    System.out.print("Grupo: ");
                    String grupo = sc.nextLine();
                    System.out.print("Mensaje: ");
                    String mensaje = sc.nextLine();
                    sistema.enviarMensaje(correo, grupo, mensaje);
                }
                case 7 -> {
                    System.out.print("Grupo: ");
                    String grupo = sc.nextLine();
                    sistema.verChat(grupo).forEach(System.out::println);
                }
                case 8 -> {
                    System.out.print("Nombre del grupo: ");
                    String grupo = sc.nextLine();
                    System.out.print("Descripción de sesión: ");
                    String desc = sc.nextLine();
                    sistema.crearSesion(correo, grupo, desc);
                }
                case 9 -> {
                    System.out.print("Nombre del grupo: ");
                    String grupo = sc.nextLine();
                    sistema.verSesiones(grupo).forEach(System.out::println);
                }
                case 0 -> System.out.println("¡Hasta luego!");
                default -> System.out.println("Opción inválida.");
            }
        }
        sc.close();
    }
}
