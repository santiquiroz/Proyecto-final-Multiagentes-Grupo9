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
                
                String contenido = "Hola residente, este mensaje tiene el consecutivo: "+resultConsult.get(0)
            	+", fue generado en: "+resultConsult.get(1)
            	+", su deuda es de: "+resultConsult.get(2)
            	+", este mensaje tiene por destinatario el residente con id: "+resultConsult.get(3)
            	+", "+resultConsult.get(4);

                InfoNotificacion infoNot = new InfoNotificacion();
                infoNot.setIdentificacionUsuario(idUser);
                infoNot.setContenido(contenido);

                PredicadoInfoNotificacion pInfoNot = new PredicadoInfoNotificacion();
                pInfoNot.setSlotInfoNotificacio(infoNot);

                agent.getContentManager().fillContent(msg, pInfoNot);
                agent.send(msg);
                
                DataBase db = new DataBase("jdbc:mysql://localhost:3306/datosadmon");
                String[] datos=infoNot.getContenido().split("_");
                String cons = resultConsult.get(0);
                String id = infoNot.getIdentificacionUsuario();
                
                //System.out.println(cons+"--------------------------------------------------------------------");
                //System.out.println(id+"--------------------------------------------------------------------");
                
                String update = "UPDATE cobro SET notificado = '1' WHERE cobro.consecutivo = "+cons+" AND cobro.id_usuario = '"+id+"'";
                db.update(update);
                
            }

        } catch (Codec.CodecException e) {
            e.printStackTrace();
        } catch (OntologyException e) {
            e.printStackTrace();
        }catch (Exception e) {
        	e.printStackTrace();
        }

    }

    public ArrayList<String> consultar() {

        DataBase db = new DataBase("jdbc:mysql://localhost:3306/datosadmon");
        ArrayList resultado = db.select("SELECT * FROM cobro WHERE notificado = 0");

        int tamano = ((ArrayList) (resultado.get(0))).size();
        if (tamano > 0) {
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
