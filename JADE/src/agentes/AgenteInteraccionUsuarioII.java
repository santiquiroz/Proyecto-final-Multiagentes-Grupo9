/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agentes;

import interfaz.InteraccionUsuario2;
import Comportamientos.ComportamientoPedidorDeCedula;
import interfaz.*;
import jade.content.lang.Codec;
import jade.content.lang.sl.SLCodec;
import jade.content.onto.Ontology;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import ontologias.InteraccionUsuarioOntology;

/**
 *
 * @author David Ospina
 */
public class AgenteInteraccionUsuarioII extends Agent {

    public Codec codec = new SLCodec();
    public Ontology ontologia = InteraccionUsuarioOntology.getInstance();

    protected void setup() {
        DFAgentDescription dfd = new DFAgentDescription();
        ServiceDescription sd = new ServiceDescription();
        sd.setType("Interfaz");
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

        InteraccionUsuario2 ventana = new InteraccionUsuario2(this);

    }

    public void addPedidorCedula(String identifica) {
        ComportamientoPedidorDeCedula cedular = new ComportamientoPedidorDeCedula(this,identifica);
        addBehaviour(cedular);
        System.out.println("Al menos meti behaviur 3");
    }
}
