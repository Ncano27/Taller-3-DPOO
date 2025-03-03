package uniandes.dpoo.aerolinea.tarifas;

import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;
import uniandes.dpoo.aerolinea.modelo.cliente.ClienteCorporativo;

public class CalculadoraTarifasTemporadaBaja extends CalculadoraTarifas {
    
    protected static final int COSTO_POR_KM_NATURAL = 600;
    protected static final int COSTO_POR_KM_CORPORATIVO = 900;
    protected static final double DESCUENTO_PEQ = 0.02;
    protected static final double DESCUENTO_MEDIANAS = 0.1;
    protected static final double DESCUENTO_GRANDES = 0.2;

    @Override
    protected int calcularCostoBase(Vuelo vuelo, Cliente cliente) {
        int distancia = calcularDistanciaVuelo(vuelo.getRuta());
        return cliente instanceof ClienteCorporativo ? distancia * COSTO_POR_KM_CORPORATIVO : distancia * COSTO_POR_KM_NATURAL;
    }

    @Override
    protected double calcularPorcentajeDescuento(Cliente cliente) {
        if (cliente instanceof ClienteCorporativo) {
            int tamanoEmpresa = ((ClienteCorporativo) cliente).getTamanoEmpresa();
            if (tamanoEmpresa < 50) {
                return DESCUENTO_PEQ;
            } else if (tamanoEmpresa < 200) {
                return DESCUENTO_MEDIANAS;
            } else {
                return DESCUENTO_GRANDES;
            }
        }
        return 0.0;
    }
}