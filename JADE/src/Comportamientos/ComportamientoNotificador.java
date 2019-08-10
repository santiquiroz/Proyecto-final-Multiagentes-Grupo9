package Comportamientos;

import agentes.AgenteNotificador;
import jade.content.lang.Codec;
import jade.content.lang.sl.SLCodec;
import jade.content.onto.Ontology;
import jade.content.onto.OntologyException;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;
import ontologias.InfoNotificacion;
import ontologias.InteraccionUsuarioOntology;
import ontologias.PredicadoInfoNotificacion;
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
        
        try{
            
            String destinatario = "AgenteInteraccionUsuario";
            AID AgentID = new AID();
            AgentID.setLocalName(destinatario);
            ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
            msg.setSender(agent.getAID());
            msg.addReceiver(AgentID);
            msg.setLanguage(agent.codec.getName());
            msg.setOntology(agent.ontologia.getName());
            
            String idUser = "xxxxx";
            String contenido = "Mensaje por defecto.";
            
            InfoNotificacion infoNot = new InfoNotificacion();
            infoNot.setIdentificacionUsuario(idUser);
            infoNot.setContenido(contenido);
            
            PredicadoInfoNotificacion pInfoNot = new PredicadoInfoNotificacion();
            pInfoNot.setSlotInfoNotificacio(infoNot);
           
            
            agent.getContentManager().fillContent(msg, pInfoNot);
            System.out.println("Prueba");
            agent.send(msg);
                        
        }catch(Codec.CodecException e){e.printStackTrace();} 
         catch (OntologyException e) { e.printStackTrace();}
        
    }

}
