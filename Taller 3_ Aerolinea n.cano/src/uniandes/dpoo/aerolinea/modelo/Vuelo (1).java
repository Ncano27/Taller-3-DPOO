package uniandes.dpoo.aerolinea.modelo;

import java.util.Collection;
import java.util.HashSet;

import uniandes.dpoo.aerolinea.tiquetes.Tiquete;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;
import uniandes.dpoo.aerolinea.tarifas.CalculadoraTarifas;
import uniandes.dpoo.aerolinea.exceptions.VueloSobrevendidoException;

public class Vuelo {
    private String fecha;
    private Avion avion;
    private Ruta ruta;
    private Collection<Tiquete> tiquetes;

    public Vuelo(Ruta ruta, String fecha, Avion avion) {
        this.fecha = fecha;
        this.avion = avion;
        this.ruta = ruta;
        this.tiquetes = new HashSet<>();
    }

    public String getFecha() {
        return fecha;
    }

    public Avion getAvion() {
        return avion;
    }

    public Ruta getRuta() {
        return ruta;
    }

    public Collection<Tiquete> getTiquetes() {
        return tiquetes;
    }

    public int venderTiquetes(Cliente cliente, CalculadoraTarifas calculadora, int cantidad) throws VueloSobrevendidoException {
        if (tiquetes.size() + cantidad > avion.getCapacidad()) {
            throw new VueloSobrevendidoException(this);
        }
        
        int vendidos = 0;
        for (int i = 0; i < cantidad; i++) {
            int tarifa = calculadora.calcularTarifa(this, cliente);
            Tiquete tiquete = new Tiquete("TQ" + (tiquetes.size() + 1), this, cliente, tarifa);
            tiquetes.add(tiquete);
            cliente.agregarTiquete(tiquete);
            vendidos++;
        }
        return vendidos;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Vuelo vuelo = (Vuelo) obj;
        return fecha.equals(vuelo.fecha);
    }
}
