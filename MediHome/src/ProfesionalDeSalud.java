import java.util.ArrayList;
import java.util.List;

public class ProfesionalDeSalud extends Usuario implements Notificable {
    private String numeroRegistroProfesional;
    private String especialidad;
    // Un profesional puede atender múltiples servicios (0..*)
    private List<ServicioDomiciliario> serviciosAtendidos = new ArrayList<>();

    public ProfesionalDeSalud() {
    }

    public ProfesionalDeSalud(String identificacion, String nombre, String correo,
                              String numeroRegistroProfesional, String especialidad) {
        super(identificacion, nombre, correo);
        this.numeroRegistroProfesional = numeroRegistroProfesional;
        this.especialidad = especialidad;
    }

    public String getNumeroRegistroProfesional() { return numeroRegistroProfesional; }
    public void setNumeroRegistroProfesional(String numeroRegistroProfesional) {
        this.numeroRegistroProfesional = numeroRegistroProfesional;
    }

    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }

    public List<ServicioDomiciliario> getServiciosAtendidos() { return serviciosAtendidos; }

    void agregarServicio(ServicioDomiciliario servicio) {
        if (!serviciosAtendidos.contains(servicio)) {
            serviciosAtendidos.add(servicio);
        }
    }

    @Override
    public void notificar(String mensaje) {
        System.out.println("[Correo a " + getCorreo() + "] Profesional " + getNombre() + ": " + mensaje);
    }
}
