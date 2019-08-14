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
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import ontologias.Notificacion;
import ontologias.Verificacion;
import system.DataBase.DataBase;

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

                            DataBase db = new DataBase("jdbc:mysql://localhost:3306/datosusuario");
                            String identify = ideUser.getTipoUsuario();
                            ArrayList resultado = db.select("SELECT rol FROM usuario WHERE identificacion = '" + identify + "'");

                            System.out.println(db.select("SELECT rol FROM usuario WHERE identificacion = '" + identify + "'"));
                            
                            String mensaje = "No esta registrado en el sistema. Es un visitante";
                            
                            if (((ArrayList) resultado.get(0)).size() == 0) {
                                System.out.println(((ArrayList) resultado.get(0)).size());
                                
                            } else {
                                String resultadoRedundante = db.getValueOn(0, 0);
                                System.out.println("El rol es: " + resultadoRedundante);
                                
                                if (resultadoRedundante.equals("propietario")) {
                                    mensaje = "Hola, persona identificada como " + identify + ", usted es un PROPIETARIO";
                                } else if (resultadoRedundante.equals("portero")) {
                                    mensaje = "Hola, persona identificada como " + identify + ", usted es un " + resultadoRedundante;
                                } else {
                                    mensaje = "No esta registrado en el sistema. Es un visitante";
                                }
                            }
                            System.out.println(mensaje);
                            Verificacion respuesta = new Verificacion();
                            respuesta.setContenidoVerificacion(mensaje);
                            ACLMessage msg2 = new ACLMessage(ACLMessage.REQUEST);
                            msg2.setLanguage(codec.getName());
                            msg2.setOntology(ontologia.getName());
                            msg2.setSender(agent.getAID());
                            msg2.addReceiver(msg.getSender());
                            agent.getContentManager().fillContent(msg2, respuesta);
                            agent.send(msg2);
                            System.out.println("Verificacion Enviada.");

                            System.out.println();
                            
                            //Ventana machete e
                            ventanaMachete v = new ventanaMachete();
                            v.jLabel1.setText(mensaje);
                            v.repaint();
                            //

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
        } catch (Exception e) {
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
    
    public class ventanaMachete extends JFrame{
            public javax.swing.JLabel jLabel1 = new JLabel("Metas aca tu ID");

        public ventanaMachete(){
            super("Identificacion completada");
            jLabel1.setPreferredSize(new Dimension(400, 30));
            this.add(jLabel1);
            this.pack();
            this.setVisible(true);
        }
    }

}
