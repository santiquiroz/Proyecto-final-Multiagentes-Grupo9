package agentes;

import Comportamientos.*;
import jade.content.lang.Codec;
import jade.content.lang.sl.SLCodec;
import jade.content.onto.Ontology;
import jade.core.Agent;
import ontologias.InteraccionUsuarioOntology;

public class AgenteInteraccionUsuario extends Agent{
    
    public Codec codec = new SLCodec();
    public Ontology ontologia = InteraccionUsuarioOntology.getInstance();
    
    @Override
    protected void setup(){
        
        getContentManager().registerLanguage(codec);
        getContentManager().registerOntology(ontologia);
        Comportamientos.ComportamientoInteraccionUsuario PingBehaviour;
        PingBehaviour = new ComportamientoInteraccionUsuario(this);
        addBehaviour(PingBehaviour);
               
        addBehaviour(new ComportamientoInteraccionUsuario(this));
        
    }
}
