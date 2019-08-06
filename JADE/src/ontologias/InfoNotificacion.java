package ontologias;

import jade.content.*;

/**
* Contiene la informacion de la notificacion a mostrar en el dispositivo 
* movil y el identificador unico del usuario a quien va dirigida dicha notificacion
* Protege name: InfoNotificacion
* @author ontology bean generator
* @version 2019/08/5, 15:32:15
*/
public class InfoNotificacion implements Concept {

   //Identificador unico para cada uno de los usuarios
   private String identificacionUsuario;
   //Como lo indica el nombre es el contenido del mensaje de notificacion para el usuario
   private String contenido;
   
   public void setIdentificacionUsuario(String value) { 
        this.identificacionUsuario=value;
   }
   public void setContenido(String value) { 
        this.contenido=value;
   }
   
   public String getIdentificacionUsuario() {
        return this.identificacionUsuario;
   }
   public String getContenido() {
        return this.contenido;
   }

}
