package agentes;

import jade.core.Agent;
import Comportamientos.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import ontologias.Notificacion;
import system.Arduino.ConexionArduino;

/**
 *
 * @author santi
 */
public class AgenteAutomatizador extends Agent{
    
    @Override
    protected void setup() {
        addBehaviour(new ComportamientoAutomatizador());
        Notificacion notificacion = new Notificacion();
        try {
            new ConexionArduino().conectar();
        } catch (IOException ex) {
            Logger.getLogger(AgenteAutomatizador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
    