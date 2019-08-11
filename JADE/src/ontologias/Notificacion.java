package ontologias;

import jade.content.*;
import jade.util.leap.*;
import jade.core.*;

/**
 * Accion que realiza el AgenteInteraccionUsuario despues de recibir la
 * informacion para las notificaciones Protege name: Notificacion
 *
 * @author ontology bean generator
 * @version 2019/08/10, 19:10:01
 */
public class Notificacion implements AgentAction {

    /**
     * Protege name: Notify
     */
    private InfoNotificacion notify;

    public void setNotify(InfoNotificacion value) {
        this.notify = value;
    }

    public InfoNotificacion getNotify() {
        return this.notify;
    }

}
