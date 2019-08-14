package Comportamientos;

import agentes.AgenteIdentificador;
import jade.content.ContentElement;
import jade.content.lang.Codec;
import jade.content.lang.sl.SLCodec;
import jade.content.onto.Ontology;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import ontologias.IdentidadUsuario;
import ontologias.InteraccionUsuarioOntology;
import ontologias.PredicadoIdentidadUsuario;
import jade.lang.acl.*;


/**
 *
 * @author David Ospina
 */
public class ComportamientoIdentificador extends SimpleBehaviour {

    public Codec codec = new SLCodec();
    public Ontology ontologia = InteraccionUsuarioOntology.getInstance();

    AgenteIdentificador agent;
    

    public ComportamientoIdentificador(Agent a) {
        super(a);
        agent = (AgenteIdentificador) a;
    }

    @Override
    public void action() {

        System.out.println("\nEsperando identificacion de la interface....");

        MessageTemplate mt = MessageTemplate.and(
                MessageTemplate.MatchLanguage(codec.getName()),
                MessageTemplate.MatchOntology(ontologia.getName())
        );
        ACLMessage msg = agent.blockingReceive(mt);

        try {

            if (msg != null) {
                if (msg.getPerformative() == ACLMessage.NOT_UNDERSTOOD) {
                    System.out.println("Mensaje NOT UNDERSTOOD recibido");
                } else {
                    if (msg.getPerformative() == ACLMessage.INFORM) {

                        ContentElement ce = agent.getContentManager().extractContent(msg);
                        if (ce instanceof PredicadoIdentidadUsuario) {
                            // Recibido un INFORM con contenido correcto
                            PredicadoIdentidadUsuario pIdentUser = (PredicadoIdentidadUsuario) ce;
                            IdentidadUsuario ideUser = pIdentUser.getInstanciaIdentidadUsuario();
                            System.out.println("Mensaje recibido:");
                            System.out.println("Rol/Identificacion: " + ideUser.getTipoUsuario());
                            

                        } else {
                            // Recibido un INFORM con contenido incorrecto
                            ACLMessage reply = msg.createReply();
                            reply.setPerformative(ACLMessage.NOT_UNDERSTOOD);
                            reply.setContent("( UnexpectedContent (expected ping))");
                            agent.send(reply);
                        }
                    } else {
                        // Recibida una performativa incorrecta
                        ACLMessage reply = msg.createReply();
                        reply.setPerformative(ACLMessage.NOT_UNDERSTOOD);
                        reply.setContent("( (Unexpected-act " + ACLMessage.getPerformative(msg.getPerformative()) + ")( expected (inform)))");
                        agent.send(reply);
                    }
                }
            } else {
                //System.out.println("No message received");
            }

        } catch (jade.content.lang.Codec.CodecException ce) {
            System.out.println(ce);
        } catch (jade.content.onto.OntologyException oe) {
            System.out.println(oe);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public boolean done() {
        return false;
    }

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
