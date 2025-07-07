public class Materia {
    private String nombre;

    public Materia(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Materia) {
            Materia otra = (Materia) obj;
            return nombre.equalsIgnoreCase(otra.getNombre());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return nombre.toLowerCase().hashCode();
    }
}
