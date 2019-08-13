package Comportamientos;

import agentes.AgenteInteraccionUsuario;
import jade.content.ContentElement;
import jade.content.lang.Codec;
import jade.content.lang.sl.SLCodec;
import jade.content.onto.Ontology;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import ontologias.InfoNotificacion;
import ontologias.InteraccionUsuarioOntology;
import ontologias.Notificacion;
import ontologias.PredicadoInfoNotificacion;
import system.mail.MailSender;
import system.DataBase.*;

public class ComportamientoInteraccionUsuario extends SimpleBehaviour {

    public Codec codec = new SLCodec();
    public Ontology ontologia = InteraccionUsuarioOntology.getInstance();

    AgenteInteraccionUsuario agent;

    public ComportamientoInteraccionUsuario(AgenteInteraccionUsuario a) {
        super(a);
        agent = (AgenteInteraccionUsuario) a;
    }

    @Override
    public void action() {
        System.out.println("\n Esperando mensaje del agenteNotificador...");
        
        MessageTemplate mt = MessageTemplate.and(
                MessageTemplate.MatchLanguage(codec.getName()),
                MessageTemplate.MatchOntology(ontologia.getName())
        );
        ACLMessage msg = agent.blockingReceive(mt);

        try {
            if (msg != null) {
                if (msg.getPerformative() == ACLMessage.NOT_UNDERSTOOD) {
                    System.out.println("Mensaje NOT UNDERSTODD recibido");
                } else {
                    if (msg.getPerformative() == ACLMessage.INFORM) {

                        ContentElement ce = agent.getContentManager().extractContent(msg);
                        if (ce instanceof PredicadoInfoNotificacion) {
                            PredicadoInfoNotificacion pInfoNot = (PredicadoInfoNotificacion) ce;
                            InfoNotificacion infoNot = pInfoNot.getSlotInfoNotificacio();
                            
                            //System.out.println("Mensaje recibido:");
                            //System.out.println("Identificacion Usuario:" + infoNot.getIdentificacionUsuario());
                            //System.out.println("contenido Mensaje:" + infoNot.getContenido());

                            //Compramos
                            
                            String contenido = infoNot.getContenido();
                            String[] contenido_spliteado = contenido.split(" ");
                            
                            for (int i = 0; i<contenido_spliteado.length; i++){
                                System.out.println("La posicion "+ i +" de este desagradable infeccion");
                                System.out.println(contenido_spliteado[i]);
                            }
                            
                            String asunto = "Este mensaje tuvo un error en la identificacion del tipo de notificacion";
                            //Este contenido tiene  caracteristicas de cobro
                            if ( contenido_spliteado[2].equals("este")){
                                asunto = "Pague la cuota de la unidad";
                            //este contenido tiene caracteristicas broadcast
                            }else if ( contenido_spliteado[2].equals("le")){
                                asunto = "Evento de copropietarios";
                            }
                            
                            
                                                        
                            new MailSender().send(
                                    "dospinao@unal.edu.co",
                                    asunto,
                                    contenido
                            );
                            
                            
                            Notificacion respuesta = new Notificacion();
                            respuesta.setNotify(infoNot);
                            ACLMessage msg2 = new ACLMessage(ACLMessage.REQUEST);
                            msg2.setLanguage(codec.getName());
                            msg2.setOntology(ontologia.getName());
                            msg2.setSender(agent.getAID());
                            msg2.addReceiver(msg.getSender());
                            agent.getContentManager().fillContent(msg2, respuesta);
                            agent.send(msg2);
                            System.out.println("Notificacion Confirmada.");
                        }

                    }
                }
            }
        } catch (Exception e) {
        }
    }

    @Override
    public boolean done() {
        return false;
    }
}
