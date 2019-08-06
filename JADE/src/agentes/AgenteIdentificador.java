package agentes;

import Comportamientos.ComportamientoIdentificador;
import jade.core.Agent;

/**
 *
 * @author santi    
 */
public class AgenteIdentificador extends Agent{
    
    @Override
    protected void setup() {
        
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
        
        addBehaviour(new ComportamientoIdentificador(this));
        
    }
}
