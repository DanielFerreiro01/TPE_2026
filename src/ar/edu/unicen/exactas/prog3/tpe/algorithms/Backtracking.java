package ar.edu.unicen.exactas.prog3.tpe.algorithms;

import ar.edu.unicen.exactas.prog3.tpe.entities.Camion;
import ar.edu.unicen.exactas.prog3.tpe.entities.Paquete;
import ar.edu.unicen.exactas.prog3.tpe.entities.Solucion;

import java.util.ArrayList;
import java.util.List;

public class Backtracking {

    private List<Camion> camiones;
    private List<Paquete> paquetes;
    private int cantEstados;

    public Backtracking(List<Camion> camiones, List<Paquete> paquetes) {
        this.camiones = camiones;
        this.paquetes = paquetes;
        this.cantEstados = 0;
    }

    /*
     * La solución se construye asignando cada paquete a un camión o dejándolo
     * sin asignar, de manera progresiva y recursiva.
     *
     * Podas aplicadas:
     * 1. Factibilidad: no se asigna un paquete a un camión si supera su capacidad
     *    o si tiene alimentos y el camión no está refrigerado.
     * 2. Optimalidad: si el peso no asignado acumulado ya iguala o supera al de
     *    la mejor solución encontrada, se descarta esa rama.
     *
     * Métrica: cantidad de estados generados (cantEstados).
     */
    public Solucion resolver() {
        this.cantEstados = 0;

        int[] asignacion = new int[paquetes.size()];
        double[] pesoCargado = new double[camiones.size()];

        Solucion mejorSolucion = new Solucion();
        double pesoTotal = 0;
        for (Paquete p : paquetes) pesoTotal += p.getPesoKg();
        mejorSolucion.setPesoNoAsignado(pesoTotal);

        back(0, asignacion, pesoCargado, 0.0, mejorSolucion);

        return mejorSolucion;
    }

    private void back(int indicePaquete,
                      int[] asignacion,
                      double[] pesoCargado,
                      double pesoNoAsignado,
                      Solucion mejorSolucion) {

        cantEstados++;

        if (indicePaquete == paquetes.size()) {
            if (pesoNoAsignado < mejorSolucion.getPesoNoAsignado()) {
                mejorSolucion.setPesoNoAsignado(pesoNoAsignado);
                mejorSolucion.setAsignacion(copiarAsignacion(asignacion));
            }
            return;
        }

        if (pesoNoAsignado >= mejorSolucion.getPesoNoAsignado()) {
            return;
        }

        Paquete paquete = paquetes.get(indicePaquete);

        for (int j = 0; j < camiones.size(); j++) {
            Camion camion = camiones.get(j);

            if (esAsignacionValida(paquete, camion, pesoCargado[j])) {
                asignacion[indicePaquete] = j;
                pesoCargado[j] += paquete.getPesoKg();

                back(indicePaquete + 1, asignacion, pesoCargado, pesoNoAsignado, mejorSolucion);

                pesoCargado[j] -= paquete.getPesoKg();
            }
        }

        asignacion[indicePaquete] = -1;
        back(indicePaquete + 1, asignacion, pesoCargado,
                pesoNoAsignado + paquete.getPesoKg(), mejorSolucion);
    }

    private boolean esAsignacionValida(Paquete paquete, Camion camion, double pesoActual) {
        if (pesoActual + paquete.getPesoKg() > camion.getCapacidadKg()) {
            return false;
        }
        if (paquete.isContieneAlimentos() && !camion.isEstaRefrigerado()) {
            return false;
        }
        return true;
    }

    private int[] copiarAsignacion(int[] asignacion) {
        int[] copia = new int[asignacion.length];
        System.arraycopy(asignacion, 0, copia, 0, asignacion.length);
        return copia;
    }

    public int getCantEstados() {
        return cantEstados;
    }

    public Solucion construirSolucionFinal(Solucion mejorSolucion) {
        Solucion resultado = new Solucion();
        int[] asignacion = mejorSolucion.getAsignacion();

        for (int j = 0; j < camiones.size(); j++) {
            resultado.getAsignaciones().put(camiones.get(j).getId(), new ArrayList<>());
        }

        for (int i = 0; i < paquetes.size(); i++) {
            if (asignacion[i] == -1) {
                resultado.getPaquetesNoAsignados().add(paquetes.get(i));
            } else {
                int idCamion = camiones.get(asignacion[i]).getId();
                resultado.getAsignaciones().get(idCamion).add(paquetes.get(i));
            }
        }

        return resultado;
    }
}