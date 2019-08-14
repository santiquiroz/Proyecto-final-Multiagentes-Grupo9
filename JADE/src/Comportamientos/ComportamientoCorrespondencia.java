/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comportamientos;

import agentes.AgenteNotificador;
import jade.content.lang.Codec;
import jade.content.onto.OntologyException;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;
import java.util.ArrayList;
import ontologias.InfoNotificacion;
import ontologias.PredicadoInfoNotificacion;
import system.DataBase.DataBase;

/**
 *
 * @author David Ospina
 */
public class ComportamientoCorrespondencia extends TickerBehaviour {

    AgenteNotificador agent;

    public ComportamientoCorrespondencia(Agent a, long period) {
        super(a, period);
        agent = (AgenteNotificador) a;
    }

    @Override
    protected void onTick() {
        try {
            ArrayList<String> resultConsult = consultarCorrespondencia();
            if (resultConsult != null) {

                String destinatario = "AgenteInteraccionUsuario";
                AID AgentID = new AID();
                AgentID.setLocalName(destinatario);
                ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
                msg.setSender(agent.getAID());
                msg.addReceiver(AgentID);
                msg.setLanguage(agent.codec.getName());
                msg.setOntology(agent.ontologia.getName());

                String idUser = resultConsult.get(0);

                String contenido = "Hola residente, tiene nueva correspondencia para el apartamento " 
                + resultConsult.get(0) + " porfavor revisar su buzon.";

                InfoNotificacion infoNot = new InfoNotificacion();
                infoNot.setIdentificacionUsuario(idUser);
                infoNot.setContenido(contenido);

                PredicadoInfoNotificacion pInfoNot = new PredicadoInfoNotificacion();
                pInfoNot.setSlotInfoNotificacio(infoNot);

                agent.getContentManager().fillContent(msg, pInfoNot);
                agent.send(msg);

                DataBase db = new DataBase("jdbc:mysql://localhost:3306/datoscorrespondencia");
                String[] datos = infoNot.getContenido().split("_");
                String cons = resultConsult.get(0);
                String id = infoNot.getIdentificacionUsuario();

                //System.out.println(cons+"--------------------------------------------------------------------");
                //System.out.println(id+"--------------------------------------------------------------------");
                String update = "UPDATE buzon SET notificado = '1' WHERE buzon.apartamento = '" + cons + "'";
                db.update(update);
            }

        } catch (Codec.CodecException e) {
            e.printStackTrace();
        } catch (OntologyException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private ArrayList<String> consultarCorrespondencia() {
        DataBase db = new DataBase("jdbc:mysql://localhost:3306/datoscorrespondencia");
        ArrayList resultado = db.select("SELECT * FROM buzon WHERE notificado = 0");

        int tamano = ((ArrayList) (resultado.get(0))).size();
        if (tamano > 0) {
            ArrayList<String> resultadoReduntante = new ArrayList<String>();
            resultadoReduntante.add(db.getValueOn(0, 0));
            resultadoReduntante.add(db.getValueOn(0, 1));
            resultadoReduntante.add(db.getValueOn(0, 2));
            return resultadoReduntante;

        }
        return null;
    }

}
