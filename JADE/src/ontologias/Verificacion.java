package ontologias;


import jade.content.*;
import jade.util.leap.*;
import jade.core.*;

/**
* Protege name: Verificacion
* @author ontology bean generator
* @version 2019/08/10, 19:10:01
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
