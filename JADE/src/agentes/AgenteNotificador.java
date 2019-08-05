/**
 *
 * @author David Ospina
 */

package agentes;

import Comportamientos.ComportamientoNotificador;
import jade.content.lang.Codec;
import jade.content.lang.sl.SLCodec;
import jade.content.onto.Ontology;
import jade.core.Agent;
import ontologias.InteraccionUsuarioOntology;

public class AgenteNotificador extends Agent{
    
    public Codec codec = new SLCodec();
    public Ontology ontologia = InteraccionUsuarioOntology.getInstance();
    
    @Override
    protected void setup(){
        
        /**
         * Esta linea se utiliza cuando se envian parametros desde
         * la declaracion de los argumentos
        
        String argumentos;
        Object[] args = getArguments();
        argumentos = args[0].toString();
        if (argumentos.equals("terminar agente")){            
            System.out.println("terminando agente");   
            doDelete();
        }
        
        */
        
        addBehaviour(new ComportamientoNotificador(this, 0));
        
    }
}
