package ontologias;


import jade.content.*;
import jade.util.leap.*;
import jade.core.*;

/**
   * Contiene la informacion de la notificación a mostrar en el dispositivo movil y el identificador unico del usuario a quien va dirigida dicha notificacion
* Protege name: InfoNotificacion
* @author ontology bean generator
* @version 2019/08/10, 16:27:01
*/
public class InfoNotificacion implements Concept {

   /**
   * Identificador unico para cada uno de los usuarios
* Protege name: IdentificacionUsuario
   */
   private String identificacionUsuario;
   public void setIdentificacionUsuario(String value) { 
    this.identificacionUsuario=value;
   }
   public String getIdentificacionUsuario() {
     return this.identificacionUsuario;
   }

   /**
   * Como lo indica el nombre es el contenido del mensaje de notificación para el usuario
* Protege name: Contenido
   */
   private String contenido;
   public void setContenido(String value) { 
    this.contenido=value;
   }
   public String getContenido() {
     return this.contenido;
   }

}
