package ar.edu.unicen.exactas.prog3.tpe.services;

import ar.edu.unicen.exactas.prog3.tpe.entities.Camion;
import ar.edu.unicen.exactas.prog3.tpe.entities.Paquete;
import ar.edu.unicen.exactas.prog3.tpe.io.CSVReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Servicios {

    private List<Camion> camiones;
    private Map<String, Paquete> paquetesPorCodigo;
    private List<Paquete> paquetesConAlimentos;
    private List<Paquete> paquetesSinAlimentos;
    private List<Paquete>[] urgenciasIndexadas;

    @SuppressWarnings("unchecked")
    public Servicios(String pathCamiones, String pathPaquetes) {
        CSVReader reader = new CSVReader();

        this.camiones = reader.leerCamiones(pathCamiones);

        List<Paquete> paquetes = reader.leerPaquetes(pathPaquetes);

        this.paquetesPorCodigo = new HashMap<>();
        this.paquetesConAlimentos = new ArrayList<>();
        this.paquetesSinAlimentos = new ArrayList<>();

        this.urgenciasIndexadas = new ArrayList[101];
        for (int i = 0; i <= 100; i++) {
            urgenciasIndexadas[i] = new ArrayList<>();
        }

        for (Paquete p : paquetes) {
            // Para Servicio 1
            paquetesPorCodigo.put(p.getCodigoPaquete(), p);

            // Para Servicio 2
            if (p.isContieneAlimentos()) {
                paquetesConAlimentos.add(p);
            } else {
                paquetesSinAlimentos.add(p);
            }

            // Para Servicio 3
            urgenciasIndexadas[p.getNivelUrgencia()].add(p);
        }
    }

    /*
     * Complejidad temporal del Servicio 1: O(1)
     * Se utiliza un HashMap<String, Paquete> donde el código del paquete es la clave.
     */
    public Paquete servicio1(String codigoPaquete) {
        return paquetesPorCodigo.get(codigoPaquete);
    }

    /*
     * Complejidad temporal del Servicio 2: O(1)
     * Se mantienen dos listas separadas desde la carga: una para paquetes con alimentos
     * y otra para paquetes sin alimentos. Retornar la lista correspondiente es O(1).
     */
    public List<Paquete> servicio2(boolean contieneAlimentos) {
        if (contieneAlimentos) {
            return new ArrayList<>(paquetesConAlimentos);
        } else {
            return new ArrayList<>(paquetesSinAlimentos);
        }
    }

    /*
     * Complejidad temporal del Servicio 3: O(resultado)
     * Se utiliza un arreglo indexado por nivel de urgencia (1 a 100).
     * El recorrido del rango acotado [urgenciaMinima, urgenciaMaxima] es O(1),
     * por lo que la complejidad es proporcional a la cantidad de paquetes retornados.
     */
    public List<Paquete> servicio3(int urgenciaMinima, int urgenciaMaxima) {
        List<Paquete> resultado = new ArrayList<>();
        for (int i = urgenciaMinima; i <= urgenciaMaxima; i++) {
            resultado.addAll(urgenciasIndexadas[i]);
        }
        return resultado;
    }

    public List<Camion> getCamiones() {
        return camiones;
    }

    public List<Paquete> getTodosLosPaquetes() {
        List<Paquete> todos = new ArrayList<>(paquetesConAlimentos);
        todos.addAll(paquetesSinAlimentos);
        return todos;
    }
}