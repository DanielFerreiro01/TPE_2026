package ar.edu.unicen.exactas.prog3.tpe.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solucion {

    private Map<Integer, List<Paquete>> asignaciones;
    private List<Paquete> paquetesNoAsignados;
    private double pesoNoAsignado;
    private int[] asignacion;

    public Solucion() {
        this.asignaciones = new HashMap<>();
        this.paquetesNoAsignados = new ArrayList<>();
        this.pesoNoAsignado = Double.MAX_VALUE;
        this.asignacion = null;
    }

    public Map<Integer, List<Paquete>> getAsignaciones() {
        return asignaciones;
    }

    public List<Paquete> getPaquetesNoAsignados() {
        return paquetesNoAsignados;
    }

    public double getPesoNoAsignado() {
        return pesoNoAsignado;
    }

    public void setPesoNoAsignado(double pesoNoAsignado) {
        this.pesoNoAsignado = pesoNoAsignado;
    }

    public int[] getAsignacion() {
        return asignacion;
    }

    public void setAsignacion(int[] asignacion) {
        this.asignacion = asignacion;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Integer, List<Paquete>> entry : asignaciones.entrySet()) {
            sb.append("  Camion ").append(entry.getKey()).append(": ");
            if (entry.getValue().isEmpty()) {
                sb.append("(sin paquetes)");
            } else {
                sb.append(entry.getValue());
            }
            sb.append("\n");
        }
        sb.append("  Paquetes no asignados: ").append(paquetesNoAsignados).append("\n");
        sb.append("  Peso no asignado: ").append(pesoNoAsignado).append(" kg.");
        return sb.toString();
    }
}