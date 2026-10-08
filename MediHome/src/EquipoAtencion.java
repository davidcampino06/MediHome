import java.util.ArrayList;
import java.util.List;

public class EquipoAtencion {
    private String codigo;
    private String nombre;
    private String zonaCobertura;
    private List<ProfesionalDeSalud> profesionales = new ArrayList<>();

    public EquipoAtencion() {
    }

    public EquipoAtencion(String codigo, String nombre, String zonaCobertura) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.zonaCobertura = zonaCobertura;
    }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getZonaCobertura() { return zonaCobertura; }
    public void setZonaCobertura(String zonaCobertura) { this.zonaCobertura = zonaCobertura; }

    public List<ProfesionalDeSalud> getProfesionales() { return profesionales; }

    public void agregarProfesional(ProfesionalDeSalud profesional) {
        if (profesional != null && !profesionales.contains(profesional)) {
            profesionales.add(profesional);
        }
    }

    public void retirarProfesional(ProfesionalDeSalud profesional) {
        profesionales.remove(profesional);
    }

    @Override
    public String toString() {
        return "Equipo " + nombre + " [" + codigo + "] - Zona: " + zonaCobertura
                + " - Profesionales: " + profesionales;
    }
}
