import java.time.LocalDateTime;

public class ServicioDomiciliario {
    public static final String SOLICITADO = "Solicitado";
    public static final String PROGRAMADO = "Programado";
    public static final String EN_ATENCION = "En atencion";
    public static final String FINALIZADO = "Finalizado";
    public static final String CANCELADO = "Cancelado";

    private String codigo;
    private LocalDateTime fechaProgramada;
    private String direccionAtencion;
    private String motivo;
    private String estado;

    private Paciente paciente;                 // 1 paciente
    private ProfesionalDeSalud profesional;    // 0..1 profesional
    private AtencionMedica atencionMedica;     // 0..1 (composición)

    public ServicioDomiciliario() {
        this.estado = SOLICITADO;
    }

    public ServicioDomiciliario(String codigo, LocalDateTime fechaProgramada,
                                String direccionAtencion, String motivo, Paciente paciente) {
        this.codigo = codigo;
        this.fechaProgramada = fechaProgramada;
        this.direccionAtencion = direccionAtencion;
        this.motivo = motivo;
        this.paciente = paciente;
        this.estado = SOLICITADO;
    }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public LocalDateTime getFechaProgramada() { return fechaProgramada; }
    public void setFechaProgramada(LocalDateTime fechaProgramada) { this.fechaProgramada = fechaProgramada; }

    public String getDireccionAtencion() { return direccionAtencion; }
    public void setDireccionAtencion(String direccionAtencion) { this.direccionAtencion = direccionAtencion; }

    public String getMotivo() { return motivo; }
    public void setMotivo(String motivo) { this.motivo = motivo; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) {
        if (!estado.equals(SOLICITADO) && !estado.equals(PROGRAMADO) && !estado.equals(EN_ATENCION)
                && !estado.equals(FINALIZADO) && !estado.equals(CANCELADO)) {
            throw new IllegalArgumentException("Estado no válido: " + estado);
        }
        this.estado = estado;
    }

    public Paciente getPaciente() { return paciente; }
    public void setPaciente(Paciente paciente) { this.paciente = paciente; }

    public ProfesionalDeSalud getProfesional() { return profesional; }

    public AtencionMedica getAtencionMedica() { return atencionMedica; }

    // Al programar el servicio se le asigna un profesional (relación "atiende")
    public void programar(ProfesionalDeSalud profesional) {
        this.profesional = profesional;
        profesional.agregarServicio(this);
        setEstado(PROGRAMADO);
        paciente.notificar("Servicio " + codigo + " programado para " + fechaProgramada
                + " con " + profesional.getNombre());
        profesional.notificar("Tiene asignado el servicio " + codigo + " en " + direccionAtencion);
    }

    // Composición "genera": la atención solo se crea desde el servicio
    public AtencionMedica iniciarAtencion(LocalDateTime fechaHoraInicio) {
        if (profesional == null) {
            throw new IllegalStateException("El servicio debe estar programado con un profesional");
        }
        if (atencionMedica == null) {
            atencionMedica = new AtencionMedica(fechaHoraInicio);
            setEstado(EN_ATENCION);
        }
        return atencionMedica;
    }

    public void finalizar(LocalDateTime fechaHoraFin, String observaciones, String recomendaciones) {
        if (atencionMedica == null) {
            throw new IllegalStateException("No hay una atención iniciada");
        }
        atencionMedica.setFechaHoraFin(fechaHoraFin);
        atencionMedica.setObservaciones(observaciones);
        atencionMedica.setRecomendaciones(recomendaciones);
        setEstado(FINALIZADO);
        paciente.notificar("Servicio " + codigo + " finalizado. Recomendaciones: " + recomendaciones);
    }

    public void cancelar() {
        setEstado(CANCELADO);
        paciente.notificar("Servicio " + codigo + " cancelado");
        if (profesional != null) {
            profesional.notificar("Servicio " + codigo + " cancelado");
        }
    }

    @Override
    public String toString() {
        return "Servicio " + codigo + " | " + fechaProgramada + " | " + direccionAtencion
                + " | Motivo: " + motivo + " | Estado: " + estado
                + " | Paciente: " + paciente
                + " | Profesional: " + (profesional != null ? profesional : "sin asignar");
    }
}
