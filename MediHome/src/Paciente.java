import java.util.ArrayList;
import java.util.List;

public class Paciente extends Usuario implements Notificable {
    private String telefono;
    private String direccion;
    // Un paciente puede solicitar varios servicios (0..*)
    private List<ServicioDomiciliario> servicios = new ArrayList<>();

    public Paciente() {
    }

    public Paciente(String identificacion, String nombre, String correo,
                    String telefono, String direccion) {
        super(identificacion, nombre, correo);
        this.telefono = telefono;
        this.direccion = direccion;
    }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public List<ServicioDomiciliario> getServicios() { return servicios; }

    // Relación "solicita": crea el servicio y lo asocia a este paciente
    public ServicioDomiciliario solicitarServicio(String codigo, java.time.LocalDateTime fechaProgramada,
                                                  String direccionAtencion, String motivo) {
        ServicioDomiciliario servicio = new ServicioDomiciliario(codigo, fechaProgramada,
                direccionAtencion, motivo, this);
        servicios.add(servicio);
        notificar("Su servicio " + codigo + " fue registrado con estado " + servicio.getEstado());
        return servicio;
    }

    @Override
    public void notificar(String mensaje) {
        System.out.println("[SMS a " + telefono + "] Paciente " + getNombre() + ": " + mensaje);
    }
}
