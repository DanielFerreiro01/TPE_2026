package ar.edu.unicen.exactas.prog3.tpe;

import ar.edu.unicen.exactas.prog3.tpe.algorithms.Backtracking;
import ar.edu.unicen.exactas.prog3.tpe.algorithms.Greedy;
import ar.edu.unicen.exactas.prog3.tpe.entities.Paquete;
import ar.edu.unicen.exactas.prog3.tpe.entities.Solucion;
import ar.edu.unicen.exactas.prog3.tpe.services.Servicios;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        String pathCamiones = "data/Camiones.csv";
        String pathPaquetes = "data/Paquetes.csv";

        Servicios servicios = new Servicios(pathCamiones, pathPaquetes);

        // --- Servicios ---
        System.out.println("=== Servicio 1 ===");
        Paquete p = servicios.servicio1("P001");
        System.out.println("Buscando P001: " + p);

        System.out.println("\n=== Servicio 2 ===");
        List<Paquete> conAlimentos = servicios.servicio2(true);
        System.out.println("Con alimentos: " + conAlimentos);

        System.out.println("\n=== Servicio 3 ===");
        List<Paquete> rango = servicios.servicio3(10, 80);
        System.out.println("Urgencia entre 10 y 80: " + rango);

        List<Paquete> todosPaquetes = servicios.getTodosLosPaquetes();

        // --- Backtracking ---
        System.out.println("\n=== Backtracking ===");
        Backtracking bt = new Backtracking(servicios.getCamiones(), todosPaquetes);
        Solucion mejorRaw = bt.resolver();
        Solucion solucionBT = bt.construirSolucionFinal(mejorRaw);
        System.out.println("Solución:");
        System.out.println(solucionBT);
        System.out.println("Métrica - Estados generados: " + bt.getCantEstados());

        // --- Greedy ---
        System.out.println("\n=== Greedy ===");
        Greedy greedy = new Greedy(servicios.getCamiones(), todosPaquetes);
        Solucion solucionGreedy = greedy.resolver();
        System.out.println("Solución:");
        System.out.println(solucionGreedy);
        System.out.println("Métrica - Iteraciones: " + greedy.getCantIteraciones());

        // --- Comparación ---
        System.out.println("\n=== Comparación ===");
        System.out.println("Backtracking - Peso no asignado: " + mejorRaw.getPesoNoAsignado() + " kg");
        System.out.println("Greedy       - Peso no asignado: " + solucionGreedy.getPesoNoAsignado() + " kg");
    }
}