package agentes;

import jade.core.Agent;
import Comportamientos.*;
import ontologias.Notificacion;

/**
 *
 * @author santi
 */
public class AgenteAutomatizador extends Agent{
    
    @Override
    protected void setup() {
        addBehaviour(new ComportamientoAutomatizador());
        Notificacion notificacion = new Notificacion();
        notificacion.notificar("squirozu@unal.edu.co,dospinao@unal.edu.co,cdramirezh@unal.edu.co", "CORREO DE PRUEBA.. puto el que lo lea", "Este es un puto correo de prueba.");
    }
}
    