package ontologias;


import jade.content.*;
import jade.util.leap.*;
import jade.core.*;

/**
* Protege name: PredicadoInfoNotificacion
* @author ontology bean generator
* @version 2019/08/10, 19:10:01
*/
public class PredicadoInfoNotificacion implements Predicate {

   /**
   * Es un predicado utilizado para enviar la informacon de la notificaión
* Protege name: SlotInfoNotificacio
   */
   private InfoNotificacion slotInfoNotificacio;
   public void setSlotInfoNotificacio(InfoNotificacion value) { 
    this.slotInfoNotificacio=value;
   }
   public InfoNotificacion getSlotInfoNotificacio() {
     return this.slotInfoNotificacio;
   }

}
