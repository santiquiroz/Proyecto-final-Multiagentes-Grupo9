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
    
    
    //
    //
    //Class comportamiento
    //
    //
    public class ComportamientoNotificador extends TickerBehaviour {

        AgenteNotificador agent;

        public ComportamientoNotificador(Agent a, long period) {
            super(a, period);
            agent = (AgenteNotificador) a;
        }

        @Override
        protected void onTick() {

            try {

                String destinatario = "AgenteInteraccionUsuario";
                AID AgentID = new AID();
                AgentID.setLocalName(destinatario);
                ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
                msg.setSender(getAID());
                msg.addReceiver(AgentID);
                msg.setLanguage(codec.getName());
                msg.setOntology(ontologia.getName());

                String idUser = "xxxxx";
                String contenido = "Mensaje por defecto.";

                InfoNotificacion infoNot = new InfoNotificacion();
                infoNot.setIdentificacionUsuario(idUser);
                infoNot.setContenido(contenido);

                PredicadoInfoNotificacion pInfoNot = new PredicadoInfoNotificacion();
                pInfoNot.setSlotInfoNotificacio(infoNot);

                getContentManager().fillContent(msg, pInfoNot);
                System.out.println("Prueba");
                send(msg);

            } catch (Codec.CodecException e) {
                e.printStackTrace();
            } catch (OntologyException e) {
                e.printStackTrace();
            }

        }
        //
        //End Comporta
        
    }

    
    
    
    
    
    
}
