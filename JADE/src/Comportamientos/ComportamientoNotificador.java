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
import java.util.ArrayList;
import ontologias.InfoNotificacion;
import ontologias.InteraccionUsuarioOntology;
import ontologias.PredicadoInfoNotificacion;
import system.DataBase.DataBase;

/**
 *
 * @author David Ospina
 */
public class ComportamientoNotificador extends TickerBehaviour {

    AgenteNotificador agent;

    public ComportamientoNotificador(Agent a, long period) {
        super(a, period);
        agent = (AgenteNotificador) a;
    }

    @Override
    protected void onTick() {

        try {
            ArrayList<String> resultConsult = consultar();
            if (resultConsult != null) {
                
                String destinatario = "AgenteInteraccionUsuario";
                AID AgentID = new AID();
                AgentID.setLocalName(destinatario);
                ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
                msg.setSender(agent.getAID());
                msg.addReceiver(AgentID);
                msg.setLanguage(agent.codec.getName());
                msg.setOntology(agent.ontologia.getName());

                String idUser = resultConsult.get(3);
                String contenido = resultConsult.get(0)+"_"+resultConsult.get(1)+"_"+resultConsult.get(2)+"_"+resultConsult.get(3)+"_"+resultConsult.get(4);

                InfoNotificacion infoNot = new InfoNotificacion();
                infoNot.setIdentificacionUsuario(idUser);
                infoNot.setContenido(contenido);

                PredicadoInfoNotificacion pInfoNot = new PredicadoInfoNotificacion();
                pInfoNot.setSlotInfoNotificacio(infoNot);

                agent.getContentManager().fillContent(msg, pInfoNot);
                agent.send(msg);
            }

        } catch (Codec.CodecException e) {
            e.printStackTrace();
        } catch (OntologyException e) {
            e.printStackTrace();
        }

    }

    public ArrayList<String> consultar() {

        DataBase db = new DataBase("jdbc:mysql://localhost:3306/datosadmon");
        ArrayList resultado = db.select("SELECT * FROM cobro WHERE notificado = 0");

        int tamaño = ((ArrayList) (resultado.get(0))).size();
        if (tamaño > 0) {
            ArrayList<String> resultadoReduntante = new ArrayList<String>();
            resultadoReduntante.add(db.getValueOn(0, 0));
            resultadoReduntante.add(db.getValueOn(0, 1));
            resultadoReduntante.add(db.getValueOn(0, 2));
            resultadoReduntante.add(db.getValueOn(0, 3));
            resultadoReduntante.add(db.getValueOn(0, 4));
            return resultadoReduntante;
        }
        return null;

    }
}
