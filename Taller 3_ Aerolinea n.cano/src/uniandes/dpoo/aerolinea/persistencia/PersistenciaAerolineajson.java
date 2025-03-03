package uniandes.dpoo.aerolinea.persistencia;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.JSONObject;
import org.json.JSONTokener;
import uniandes.dpoo.aerolinea.exceptions.InformacionInconsistenteException;
import uniandes.dpoo.aerolinea.exceptions.TipoInvalidoException;
import uniandes.dpoo.aerolinea.modelo.Aerolinea;

public class PersistenciaAerolineaJSON implements IPersistenciaAerolinea {

    @Override
    public void cargarAerolinea(String archivo, Aerolinea aerolinea) throws IOException, TipoInvalidoException, InformacionInconsistenteException {
        try (FileReader reader = new FileReader(archivo)) {
            JSONTokener tokener = new JSONTokener(reader);
            JSONObject json = new JSONObject(tokener);
            // Implementar lógica de carga de la aerolínea
        }
    }

    @Override
    public void salvarAerolinea(String archivo, Aerolinea aerolinea) throws IOException, TipoInvalidoException {
        try (FileWriter writer = new FileWriter(archivo)) {
            JSONObject json = new JSONObject();
            // Implementar lógica para extraer datos de la aerolínea y guardarlos en JSON
            writer.write(json.toString(4)); // Indentación de 4 espacios
        }
    }
}