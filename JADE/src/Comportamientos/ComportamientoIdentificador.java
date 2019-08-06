package Comportamientos;

import agentes.AgenteIdentificador;
import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;

/**
 *
 * @author David Ospina
 */
public class ComportamientoIdentificador extends SimpleBehaviour{
    
    AgenteIdentificador agent;
    
    public ComportamientoIdentificador(Agent a) {
        super(a);
        agent = (AgenteIdentificador) a;
    }

    @Override
    public void action() {
    }

    @Override
    public boolean done() {
        return false;
    }
    
}
