package ontologias;


import jade.content.*;
import jade.util.leap.*;
import jade.core.*;

/**
   * Es el tipo de usuario que ingresa en el sistem (puede ser un propietario, visitante o portero)
* Protege name: IdentidadUsuario
* @author ontology bean generator
* @version 2019/08/5, 15:32:15
*/
public class IdentidadUsuario implements Concept {

   /**
   * por medio del agente identificador se reconoce el tipo de usuario que desea interactuar. Esto es necesario pues dependiendo del tipo de usuario se proporcionar� una interacci�n distinta.
* Protege name: TipoUsuario
   */
   private String tipoUsuario;
   public void setTipoUsuario(String value) { 
    this.tipoUsuario=value;
   }
   public String getTipoUsuario() {
     return this.tipoUsuario;
   }

}
