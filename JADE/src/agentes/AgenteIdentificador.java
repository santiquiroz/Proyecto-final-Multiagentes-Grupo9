package agentes;

import Comportamientos.ComportamientoIdentificador;
import jade.content.lang.Codec;
import jade.content.lang.sl.SLCodec;
import jade.content.onto.Ontology;
import jade.core.Agent;
import ontologias.InteraccionUsuarioOntology;

/**
 *
 * @author santiQuiroz
 */
public class AgenteIdentificador extends Agent {

    public Codec codec = new SLCodec();
    public Ontology ontologia = InteraccionUsuarioOntology.getInstance();

    @Override
    protected void setup() {

        getContentManager().registerLanguage(codec);
        getContentManager().registerOntology(ontologia);
        
        ComportamientoIdentificador identifBehaviour;
        identifBehaviour = new ComportamientoIdentificador(this);
        addBehaviour(identifBehaviour);

    }
}
