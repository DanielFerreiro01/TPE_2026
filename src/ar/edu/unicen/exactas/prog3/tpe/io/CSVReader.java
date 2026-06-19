package ar.edu.unicen.exactas.prog3.tpe.io;

import ar.edu.unicen.exactas.prog3.tpe.entities.Camion;
import ar.edu.unicen.exactas.prog3.tpe.entities.Paquete;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {

    public List<Camion> leerCamiones(String path) {
        List<Camion> camiones = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            int total = Integer.parseInt(br.readLine().trim());
            for (int i = 0; i < total; i++) {
                String linea = br.readLine();
                if (linea == null) break;
                String[] partes = linea.trim().split(";");
                int id = Integer.parseInt(partes[0]);
                String patente = partes[1];
                boolean refrigerado = partes[2].equals("1");
                double capacidad = Double.parseDouble(partes[3]);
                camiones.add(new Camion(id, patente, refrigerado, capacidad));
            }
        } catch (IOException e) {
            System.err.println("Error leyendo camiones: " + e.getMessage());
        }
        return camiones;
    }

    public List<Paquete> leerPaquetes(String path) {
        List<Paquete> paquetes = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            int total = Integer.parseInt(br.readLine().trim());
            for (int i = 0; i < total; i++) {
                String linea = br.readLine();
                if (linea == null) break;
                String[] partes = linea.trim().split(";");
                int id = Integer.parseInt(partes[0]);
                String codigo = partes[1];
                double peso = Double.parseDouble(partes[2]);
                boolean contieneAlimentos = partes[3].equals("1");
                int urgencia = Integer.parseInt(partes[4]);
                paquetes.add(new Paquete(id, codigo, peso, contieneAlimentos, urgencia));
            }
        } catch (IOException e) {
            System.err.println("Error leyendo paquetes: " + e.getMessage());
        }
        return paquetes;
    }
}