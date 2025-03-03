package uniandes.dpoo.aerolinea.tiquetes;

import uniandes.dpoo.aerolinea.modelo.Aerolinea;
import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;

public class Tiquete {
    private String codigo;
    private int tarifa;
    private boolean usado;
    private Vuelo Vuelo;
    private Cliente cliente;

    public Tiquete(String codigo, Vuelo vuelo,Cliente clienteComprador,int tarifa) {
        this.codigo = codigo;
        this.tarifa = tarifa;
        this.usado = usado;
        Vuelo= vuelo;
        cliente= clienteComprador;
    }
    public Cliente getCliente() {
        return cliente;
    }

    public String getCodigo() {
        return codigo;
    }

    public int getTarifa() {
        return tarifa;
    }

    public Vuelo getVuelo(Aerolinea aerolinea) {
        return Vuelo;
    }

   
    public void marcarComoUsado() {
        this.usado = true;
    }
    public boolean esUsado() {
        return usado;
    }
}
