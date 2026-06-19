package ar.edu.unicen.exactas.prog3.tpe.algorithms;

import ar.edu.unicen.exactas.prog3.tpe.entities.Camion;
import ar.edu.unicen.exactas.prog3.tpe.entities.Paquete;
import ar.edu.unicen.exactas.prog3.tpe.entities.Solucion;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Greedy {

    private List<Camion> camiones;
    private List<Paquete> paquetes;
    private int cantIteraciones;

    public Greedy(List<Camion> camiones, List<Paquete> paquetes) {
        this.camiones = camiones;
        this.paquetes = paquetes;
        this.cantIteraciones = 0;
    }

    /*
     * Estrategia greedy aplicada:
     *
     * - Candidatos (C): todos los paquetes, ordenados de mayor a menor urgencia.
     * - Candidatos seleccionados (S): paquetes asignados a algún camión.
     * - Función Selección: el paquete de mayor nivel de urgencia. Se pre-ordena
     *   la lista y se itera en ese orden.
     * - Función Factible: el paquete entra en el camión si no supera su capacidad
     *   y, si tiene alimentos, el camión debe estar refrigerado.
     * - Función Solución: se procesaron todos los candidatos.
     * - Función Objetivo: minimizar el peso total no asignado.
     *
     * El greedy no garantiza la solución óptima, pero la obtiene en tiempo
     * polinomial a diferencia del backtracking.
     */
    public Solucion resolver() {
        this.cantIteraciones = 0;

        Solucion solucion = new Solucion();

        for (Camion c : camiones) {
            solucion.getAsignaciones().put(c.getId(), new ArrayList<>());
        }

        double[] pesoCargado = new double[camiones.size()];

        List<Paquete> candidatos = new ArrayList<>(paquetes);
        candidatos.sort(Comparator.comparingInt(Paquete::getNivelUrgencia).reversed());

        for (Paquete paquete : candidatos) {
            cantIteraciones++;

            int mejorIndiceCamion = -1;
            double menorEspacioRestante = Double.MAX_VALUE;

            for (int j = 0; j < camiones.size(); j++) {
                Camion camion = camiones.get(j);
                double espacioRestante = camion.getCapacidadKg() - pesoCargado[j];

                if (esFactible(paquete, camion, pesoCargado[j])) {
                    if (espacioRestante < menorEspacioRestante) {
                        menorEspacioRestante = espacioRestante;
                        mejorIndiceCamion = j;
                    }
                }
            }

            if (mejorIndiceCamion != -1) {
                Camion camionElegido = camiones.get(mejorIndiceCamion);
                solucion.getAsignaciones().get(camionElegido.getId()).add(paquete);
                pesoCargado[mejorIndiceCamion] += paquete.getPesoKg();
            } else {
                solucion.getPaquetesNoAsignados().add(paquete);
            }
        }

        double pesoNoAsignado = 0;
        for (Paquete p : solucion.getPaquetesNoAsignados()) {
            pesoNoAsignado += p.getPesoKg();
        }
        solucion.setPesoNoAsignado(pesoNoAsignado);

        return solucion;
    }

    private boolean esFactible(Paquete paquete, Camion camion, double pesoActual) {
        if (pesoActual + paquete.getPesoKg() > camion.getCapacidadKg()) {
            return false;
        }
        if (paquete.isContieneAlimentos() && !camion.isEstaRefrigerado()) {
            return false;
        }
        return true;
    }

    public int getCantIteraciones() {
        return cantIteraciones;
    }
}