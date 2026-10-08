import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== MediHome - Atención médica domiciliaria ===\n");

        // Usuarios
        Paciente paciente = new Paciente("1001", "Ana Gómez", "ana@correo.com",
                "3001234567", "Calle 10 # 5-20");
        ProfesionalDeSalud medico = new ProfesionalDeSalud("2001", "Dr. Carlos Ruiz",
                "carlos@medihome.com", "RP-4521", "Medicina general");
        ProfesionalDeSalud enfermera = new ProfesionalDeSalud("2002", "Laura Pérez",
                "laura@medihome.com", "RP-7788", "Enfermería");

        // Equipo de atención (agregación)
        EquipoAtencion equipoNorte = new EquipoAtencion("EQ-01", "Equipo Norte", "Zona Norte");
        equipoNorte.agregarProfesional(medico);
        equipoNorte.agregarProfesional(enfermera);
        System.out.println(equipoNorte + "\n");

        // El paciente solicita un servicio
        ServicioDomiciliario servicio = paciente.solicitarServicio("SD-001",
                LocalDateTime.of(2026, 10, 10, 9, 0), paciente.getDireccion(), "Fiebre alta");

        // Se programa y se asigna un profesional
        servicio.programar(medico);

        // Se genera la atención médica (composición)
        AtencionMedica atencion = servicio.iniciarAtencion(LocalDateTime.of(2026, 10, 10, 9, 15));
        System.out.println("\nEstado: " + servicio.getEstado());

        // Se registran mediciones de signos vitales (composición)
        System.out.println("Registrando mediciones:");
        atencion.registrarMedicion(LocalDateTime.of(2026, 10, 10, 9, 20), 38.7, 95, 120, 80, 97.0);
        atencion.registrarMedicion(LocalDateTime.of(2026, 10, 10, 9, 50), 37.9, 88, 118, 78, 98.0);

        // Se finaliza el servicio
        System.out.println();
        servicio.finalizar(LocalDateTime.of(2026, 10, 10, 10, 0),
                "Cuadro viral leve", "Reposo, hidratación y acetaminofén cada 8 horas");

        System.out.println("\n" + servicio);
        System.out.println(atencion);

        // Un profesional cambia de equipo sin dejar de existir
        EquipoAtencion equipoSur = new EquipoAtencion("EQ-02", "Equipo Sur", "Zona Sur");
        equipoNorte.retirarProfesional(enfermera);
        equipoSur.agregarProfesional(enfermera);
        System.out.println("\n" + equipoNorte);
        System.out.println(equipoSur);
    }
}
