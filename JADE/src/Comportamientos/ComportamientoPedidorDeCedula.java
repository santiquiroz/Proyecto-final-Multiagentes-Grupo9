package Comportamientos;

import agentes.AgenteInteraccionUsuarioII;
import agentes.AgenteIdentificador;
import agentes.AgenteInteraccionUsuario;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;
import ontologias.IdentidadUsuario;
import ontologias.PredicadoIdentidadUsuario;

/**
 *
 * @author David Ospina
 */
public class ComportamientoPedidorDeCedula extends OneShotBehaviour {

    //AgenteInteraccionUsuario agent;
    AgenteInteraccionUsuarioII agent;
    String identificacion;

    public ComportamientoPedidorDeCedula(Agent a, String idetif) {
        super(a);
        agent = (AgenteInteraccionUsuarioII) a;
        this.identificacion = idetif ;
    }

    @Override
    public void action() {
        System.out.println("EnTRO A METODO --------------- ENTRO A METODO");
        //Implementacion(o llamada de) de la interface grafica
         
        
        try {
            
            String destinatario = "AgenteIdentificador";
            AID AgentID = new AID();
            AgentID.setLocalName(destinatario);
            ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
            msg.setSender(agent.getAID());
            msg.addReceiver(AgentID);
            msg.setLanguage(agent.codec.getName());
            msg.setOntology(agent.ontologia.getName());

            IdentidadUsuario ideUser = new IdentidadUsuario();
            ideUser.setTipoUsuario(identificacion);

            PredicadoIdentidadUsuario pIdeUser = new PredicadoIdentidadUsuario();
            pIdeUser.setInstanciaIdentidadUsuario(ideUser);

            agent.getContentManager().fillContent(msg, pIdeUser);
            agent.send(msg);
            
            //CodecException
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

//    @Override
//    public boolean done() {
//        return false;
//    }

    private void enviarIdentificacion(String identificacion) {
        
        try {
            
            String destinatario = "AgenteIdentificador";
            AID AgentID = new AID();
            AgentID.setLocalName(destinatario);
            ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
            msg.setSender(agent.getAID());
            msg.addReceiver(AgentID);
            msg.setLanguage(agent.codec.getName());
            msg.setOntology(agent.ontologia.getName());

            IdentidadUsuario ideUser = new IdentidadUsuario();
            ideUser.setTipoUsuario(identificacion);

            PredicadoIdentidadUsuario pIdeUser = new PredicadoIdentidadUsuario();
            pIdeUser.setInstanciaIdentidadUsuario(ideUser);

            agent.getContentManager().fillContent(msg, pIdeUser);
            agent.send(msg);
            //CodecException
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
