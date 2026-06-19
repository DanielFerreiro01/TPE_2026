package ar.edu.unicen.exactas.prog3.tpe.entities;

public class Camion {

    private int id;
    private String patente;
    private boolean estaRefrigerado;
    private double capacidadKg;

    public Camion(int id, String patente, boolean estaRefrigerado, double capacidadKg) {
        this.id = id;
        this.patente = patente;
        this.estaRefrigerado = estaRefrigerado;
        this.capacidadKg = capacidadKg;
    }

    public int getId() { return id; }
    public String getPatente() { return patente; }
    public boolean isEstaRefrigerado() { return estaRefrigerado; }
    public double getCapacidadKg() { return capacidadKg; }

    @Override
    public String toString() {
        return "Camion{id=" + id + ", patente='" + patente + "', refrigerado=" + estaRefrigerado
                + ", capacidad=" + capacidadKg + "kg}";
    }
}