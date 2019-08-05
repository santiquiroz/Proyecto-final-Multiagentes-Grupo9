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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean done() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
