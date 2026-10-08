import java.time.LocalDateTime;

public class MedicionDeSignosVitales {
    private LocalDateTime fechaHora;
    private double temperatura;
    private int frecuenciaCardiaca;
    private int presionSistolica;
    private int presionDiastolica;
    private double saturacionOxigeno;

    // Constructor de paquete: solo AtencionMedica la crea
    MedicionDeSignosVitales(LocalDateTime fechaHora, double temperatura, int frecuenciaCardiaca,
                            int presionSistolica, int presionDiastolica, double saturacionOxigeno) {
        this.fechaHora = fechaHora;
        this.temperatura = temperatura;
        this.frecuenciaCardiaca = frecuenciaCardiaca;
        this.presionSistolica = presionSistolica;
        this.presionDiastolica = presionDiastolica;
        this.saturacionOxigeno = saturacionOxigeno;
    }

    public LocalDateTime getFechaHora() { return fechaHora; }
    public void setFechaHora(LocalDateTime fechaHora) { this.fechaHora = fechaHora; }

    public double getTemperatura() { return temperatura; }
    public void setTemperatura(double temperatura) { this.temperatura = temperatura; }

    public int getFrecuenciaCardiaca() { return frecuenciaCardiaca; }
    public void setFrecuenciaCardiaca(int frecuenciaCardiaca) { this.frecuenciaCardiaca = frecuenciaCardiaca; }

    public int getPresionSistolica() { return presionSistolica; }
    public void setPresionSistolica(int presionSistolica) { this.presionSistolica = presionSistolica; }

    public int getPresionDiastolica() { return presionDiastolica; }
    public void setPresionDiastolica(int presionDiastolica) { this.presionDiastolica = presionDiastolica; }

    public double getSaturacionOxigeno() { return saturacionOxigeno; }
    public void setSaturacionOxigeno(double saturacionOxigeno) { this.saturacionOxigeno = saturacionOxigeno; }

    public void realizarMedicion() {
        System.out.println("  Medición " + fechaHora + " -> Temp: " + temperatura + " °C, FC: "
                + frecuenciaCardiaca + " lpm, PA: " + presionSistolica + "/" + presionDiastolica
                + " mmHg, SpO2: " + saturacionOxigeno + " %");
    }
}
