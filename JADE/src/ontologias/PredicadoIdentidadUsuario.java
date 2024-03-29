package ontologias;


import jade.content.*;
import jade.util.leap.*;
import jade.core.*;

/**
* Protege name: PredicadoIdentidadUsuario
* @author ontology bean generator
* @version 2019/08/10, 19:10:01
*/
public class PredicadoIdentidadUsuario implements Predicate {

   /**
   * Contiene una instancia de la identidad de un usuario que haya ingresado al sistema
* Protege name: InstanciaIdentidadUsuario
   */
   private IdentidadUsuario instanciaIdentidadUsuario;
   public void setInstanciaIdentidadUsuario(IdentidadUsuario value) { 
    this.instanciaIdentidadUsuario=value;
   }
   public IdentidadUsuario getInstanciaIdentidadUsuario() {
     return this.instanciaIdentidadUsuario;
   }

}
