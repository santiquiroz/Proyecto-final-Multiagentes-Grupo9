package ontology;


import jade.content.*;
import jade.util.leap.*;
import jade.core.*;

/**
* Protege name: Verificacion
* @author ontology bean generator
* @version 2019/08/5, 15:32:15
*/
public class Verificacion implements AgentAction {

   /**
   * Mensaje que se e muestra al usuario despues de haber intentado registrarse
* Protege name: ContenidoVerificacion
   */
   private String contenidoVerificacion;
   public void setContenidoVerificacion(String value) { 
    this.contenidoVerificacion=value;
   }
   public String getContenidoVerificacion() {
     return this.contenidoVerificacion;
   }

}
