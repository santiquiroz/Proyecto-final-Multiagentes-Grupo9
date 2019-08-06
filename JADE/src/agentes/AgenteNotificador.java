package agentes;

import Comportamientos.ComportamientoNotificador;
import jade.content.lang.Codec;
import jade.content.lang.sl.SLCodec;
import jade.content.onto.Ontology;
import jade.core.Agent;
import ontologias.InteraccionUsuarioOntology;

/**
 *
 * @author David Ospina
 */

public class AgenteNotificador extends Agent{
        
    @Override
    protected void setup(){
               
        addBehaviour(new ComportamientoNotificador(this, 1000));
        
    }
}
