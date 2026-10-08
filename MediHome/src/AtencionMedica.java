import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AtencionMedica {
    private LocalDateTime fechaHoraInicio;
    private LocalDateTime fechaHoraFin;
    private String observaciones;
    private String recomendaciones;
    private List<MedicionDeSignosVitales> mediciones = new ArrayList<>();

    // Constructor de paquete: solo ServicioDomiciliario la crea
    AtencionMedica(LocalDateTime fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }

    public LocalDateTime getFechaHoraInicio() { return fechaHoraInicio; }
    public void setFechaHoraInicio(LocalDateTime fechaHoraInicio) { this.fechaHoraInicio = fechaHoraInicio; }

    public LocalDateTime getFechaHoraFin() { return fechaHoraFin; }
    public void setFechaHoraFin(LocalDateTime fechaHoraFin) { this.fechaHoraFin = fechaHoraFin; }

    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }

    public String getRecomendaciones() { return recomendaciones; }
    public void setRecomendaciones(String recomendaciones) { this.recomendaciones = recomendaciones; }

    public List<MedicionDeSignosVitales> getMediciones() { return mediciones; }

    public MedicionDeSignosVitales registrarMedicion(LocalDateTime fechaHora, double temperatura,
                                                     int frecuenciaCardiaca, int presionSistolica,
                                                     int presionDiastolica, double saturacionOxigeno) {
        MedicionDeSignosVitales medicion = new MedicionDeSignosVitales(fechaHora, temperatura,
                frecuenciaCardiaca, presionSistolica, presionDiastolica, saturacionOxigeno);
        mediciones.add(medicion);
        medicion.realizarMedicion();
        return medicion;
    }

    @Override
    public String toString() {
        return "Atención: inicio " + fechaHoraInicio + ", fin " + fechaHoraFin
                + "\n  Observaciones: " + observaciones
                + "\n  Recomendaciones: " + recomendaciones
                + "\n  Mediciones registradas: " + mediciones.size();
    }
}
