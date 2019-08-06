package agentes;

import jade.core.Agent;
import Comportamientos.*;

/**
 *
 * @author santi
 */
public class AgenteAutomatizador extends Agent{
    
    @Override
    protected void setup() {
        addBehaviour(new ComportamientoAutomatizador());
    }
}
    