package ar.edu.unicen.exactas.prog3.tpe.entities;

public class Paquete {

    private int id;
    private String codigoPaquete;
    private double pesoKg;
    private boolean contieneAlimentos;
    private int nivelUrgencia;

    public Paquete(int id, String codigoPaquete, double pesoKg, boolean contieneAlimentos, int nivelUrgencia) {
        this.id = id;
        this.codigoPaquete = codigoPaquete;
        this.pesoKg = pesoKg;
        this.contieneAlimentos = contieneAlimentos;
        this.nivelUrgencia = nivelUrgencia;
    }

    public int getId() { return id; }
    public String getCodigoPaquete() { return codigoPaquete; }
    public double getPesoKg() { return pesoKg; }
    public boolean isContieneAlimentos() { return contieneAlimentos; }
    public int getNivelUrgencia() { return nivelUrgencia; }

    @Override
    public String toString() {
        return "Paquete{id=" + id + ", codigo='" + codigoPaquete + "', peso=" + pesoKg
                + "kg, alimentos=" + contieneAlimentos + ", urgencia=" + nivelUrgencia + "}";
    }
}