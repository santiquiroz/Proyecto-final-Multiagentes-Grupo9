package Comportamientos;

import agentes.AgenteNotificador;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;

/**
 *
 * @author David Ospina
 */
public class ComportamientoNotificador extends TickerBehaviour{
    
    AgenteNotificador agent;
    
    public ComportamientoNotificador(Agent a, long period) {
        super(a, period);
        agent = (AgenteNotificador) a;
    }

    @Override
    protected void onTick() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
