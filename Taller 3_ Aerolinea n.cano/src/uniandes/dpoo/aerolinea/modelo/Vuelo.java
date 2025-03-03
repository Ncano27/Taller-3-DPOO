package uniandes.dpoo.aerolinea.modelo;

import java.util.Collection;
import uniandes.dpoo.aerolinea.tiquetes.Tiquete;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;
import uniandes.dpoo.aerolinea.tarifas.CalculadoraTarifas;
import uniandes.dpoo.aerolinea.exceptions.VueloSobrevendidoException;

public class Vuelo {
    private String fecha;
    private Avion avion;
    private Ruta ruta;

    public Vuelo(Ruta ruta, String fecha, Avion avion ) {
        this.fecha = fecha;
        avion= avion;
        ruta=ruta;
    }

    public String getFecha() {
        return fecha;
    }
    
    public Avion getAvion(Aerolinea aerolinea) {
        return avion;
    }
    
    public Ruta getRuta(Aerolinea aerolinea) {
        return ruta;
    }
    
    public Collection<Tiquete> getTiquetes(Aerolinea aerolinea) {
        return aerolinea.getTiquetesDeVuelo(this);
    }
    
    public int venderTiquetes(Aerolinea aerolinea, Cliente cliente, CalculadoraTarifas calculadora, int cantidad) throws VueloSobrevendidoException {
        Avion avion = aerolinea.getAvionDeVuelo(this);
        Collection<Tiquete> tiquetes = aerolinea.getTiquetesDeVuelo(this);
        
        if (tiquetes.size() + cantidad > avion.getCapacidad()) {
            throw new VueloSobrevendidoException(this);
        }
        
        int vendidos = 0;
        for (int i = 0; i < cantidad; i++) {
            int tarifa = calculadora.calcularTarifa(this, cliente);
            Tiquete tiquete = new Tiquete("TQ" + (tiquetes.size() + 1), this, cliente, tarifa);
            aerolinea.registrarTiquete(tiquete);
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
