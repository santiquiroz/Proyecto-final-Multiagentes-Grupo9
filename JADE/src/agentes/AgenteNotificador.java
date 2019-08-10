package agentes;

import jade.content.lang.Codec;
import jade.content.lang.sl.SLCodec;
import jade.content.onto.Ontology;
import jade.content.onto.OntologyException;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;
import ontologias.InfoNotificacion;
import ontologias.InteraccionUsuarioOntology;
import ontologias.PredicadoInfoNotificacion;
import Comportamientos.ComportamientoNotificador;

/**
 *
 * @author David Ospina
 */
public class AgenteNotificador extends Agent {
    
    public Codec codec = new SLCodec();
    public Ontology ontologia = InteraccionUsuarioOntology.getInstance();

    @Override
    protected void setup() {

        DFAgentDescription dfd = new DFAgentDescription();
        ServiceDescription sd = new ServiceDescription();
        sd.setType("Notificador");
        sd.setName(getName());
        sd.setOwnership("ARNOIA");
        dfd.setName(getAID());
        dfd.addServices(sd);
        try {
            DFService.register(this, dfd);
        } catch (FIPAException e) {
            System.err.println(getLocalName() + " registration with DF unsucceeded. Reason: " + e.getMessage());
            doDelete();
        }

        getContentManager().registerLanguage(codec);
        getContentManager().registerOntology(ontologia);

        ComportamientoNotificador EnviarNotiBehaviour = new ComportamientoNotificador(this, 1000);
        addBehaviour(EnviarNotiBehaviour);

    }
    
}
