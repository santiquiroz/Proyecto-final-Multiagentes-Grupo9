package Comportamientos;

import agentes.AgenteNotificador;
import jade.core.AID;
import java.util.ArrayList;

import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;
import ontologias.InfoNotificacion;
import ontologias.PredicadoInfoNotificacion;
import sun.management.resources.agent;
import system.DataBase.DataBase;

public class ComportamientoBroadcast extends TickerBehaviour {

    AgenteNotificador agent;

    public ComportamientoBroadcast(Agent a, long period) {
        super(a, period);
        agent = (AgenteNotificador) a;
    }

    @Override
    protected void onTick() {
        try {
            ArrayList<String> resultConsult = consultarEventos();
            if (resultConsult != null) {

                String destinatario = "AgenteInteraccionUsuario";
                AID AgentID = new AID();
                AgentID.setLocalName(destinatario);
                ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
                msg.setSender(agent.getAID());
                msg.addReceiver(AgentID);
                msg.setLanguage(agent.codec.getName());
                msg.setOntology(agent.ontologia.getName());

                String consecutivo = resultConsult.get(0);

                String contenido = "Hola residente, le recordamos que hay un evento llamado: " + resultConsult.get(1) + " pendiente para la fecha: " + resultConsult.get(2)
            	+", por favor tenerlo en cuenta y agendar el evento.";

                InfoNotificacion infoNot = new InfoNotificacion();
                infoNot.setIdentificacionUsuario(consecutivo);
                infoNot.setContenido(contenido);

                PredicadoInfoNotificacion pInfoNot = new PredicadoInfoNotificacion();
                pInfoNot.setSlotInfoNotificacio(infoNot);

                agent.getContentManager().fillContent(msg, pInfoNot);
                agent.send(msg);

                DataBase db = new DataBase("jdbc:mysql://localhost:3306/datosadmon");
                String[] datos = infoNot.getContenido().split("_");
                String cons = resultConsult.get(0);
                String id = infoNot.getIdentificacionUsuario();

                //System.out.println(cons+"--------------------------------------------------------------------");
                //System.out.println(id+"--------------------------------------------------------------------");
                String update = "UPDATE evento SET notificado = '1' WHERE evento.consecutivo = '" + cons + "'";
                db.update(update);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> consultarEventos() {

        DataBase db = new DataBase("jdbc:mysql://localhost:3306/datosadmon");
        ArrayList resultado = db.select("SELECT * FROM evento WHERE notificado = 0");

        int tamano = ((ArrayList) (resultado.get(0))).size();
        if (tamano > 0) {
            ArrayList<String> resultadoReduntante = new ArrayList<String>();
            resultadoReduntante.add(db.getValueOn(0, 0));
            resultadoReduntante.add(db.getValueOn(0, 1));
            resultadoReduntante.add(db.getValueOn(0, 2));
            resultadoReduntante.add(db.getValueOn(0, 3));
            //for (int i = 0; i < tamano; i++) {
            //    resultadoReduntante.add(db.getValueOn(0, i));
            //}
            return resultadoReduntante;
        
        }
        return null;
    }

}
