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
        }
}
    