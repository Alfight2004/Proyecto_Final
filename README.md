# 📘 Proyecto Final - InovaMent

### Plataforma Académica de Formación de Grupos de Estudio para Estudiantes de Primer Semestre

---

## 🎓 Descripción del Proyecto

**InovaMent** es una plataforma académica diseñada para facilitar la formación de grupos de estudio entre estudiantes de primer semestre en la Universidad de Las Américas (UDLA). El sistema permite a los estudiantes registrarse, seleccionar materias, configurar sus horarios y conectarse con compañeros con intereses académicos similares.

Además, ofrece funciones de chat interno, creación de grupos, sesiones de estudio y sugerencias automáticas para promover el trabajo colaborativo y mejorar el rendimiento académico.

---

## 🔧 Funcionalidades

- ✅ Registro e inicio de sesión de estudiantes
- ✅ Registro de materias cursadas
- ✅ Configuración de horarios personales
- ✅ Sugerencias automáticas de grupos de estudio
- ✅ Crear o unirse a grupos
- ✅ Chat interno entre miembros del grupo
- ✅ Crear sesiones de estudio con fecha, hora y lugar
- ✅ Visualización de sesiones programadas

---

## 👥 Roles del Proyecto

| Rol                        | Integrante                |
|---------------------------|---------------------------|
| Líder del proyecto         | Dereck Proaño             |
| Gestión de demanda         | Fernando Salazar          |
| Desarrollo y pruebas       | Alejandro Acosta          |
| Desarrollo y QA            | Dereck Proaño             |
| QA y pruebas finales       | Fernando Salazar          |

---

## 🛠️ Tecnologías utilizadas

- Lenguaje: **Java**
- Entorno: Consola / Terminal
- Herramientas: `javac`, `java`

> *Nota:* No se usa base de datos externa. Toda la información se maneja en memoria durante la ejecución.

---

## 📂 Estructura del Proyecto

📁 InovaMent/
│
├── Estudiante.java
├── Grupo.java
├── Materia.java
├── Horario.java
├── SesionEstudio.java
├── Mensaje.java
└── Main.java

yaml
Copiar
Editar

---

## 🚀 Instrucciones de uso

1. Clona o descarga el proyecto:

```bash
git clone https://github.com/tu-usuario/InovaMent.git
cd InovaMent
Compila todos los archivos:

bash
Copiar
Editar
javac *.java
Ejecuta el sistema:

bash
Copiar
Editar
java Main
Usa el menú de consola para:

Registrar nuevos estudiantes

Iniciar sesión

Configurar materias y horarios

Formar y administrar grupos de estudio

📌 Recomendaciones
Usa Java 8 o superior.

Compila siempre todos los archivos si haces cambios.

Todos los datos se almacenan temporalmente durante la ejecución del programa.

🧾 Requerimientos funcionales
Código	Requerimiento	Descripción breve
R1	Iniciar sesión	Acceso seguro con bloqueo tras 5 intentos fallidos
R2	Registrar materias	Almacenamiento de materias cursadas
R3	Configurar horarios	Definir disponibilidad horaria del estudiante
R4	Sugerencia de grupos	Sugerencias según materias y horarios similares
R5	Crear / Unirse a grupo	Gestionar participación en grupos de estudio
R6	Chat interno	Mensajería dentro del grupo
R7	Crear sesiones de estudio	Planificar fecha, hora y lugar de estudio






