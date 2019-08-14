package ontologias;


import com.fazecast.jSerialComm.SerialPort;
import jade.content.*;
import jade.util.leap.*;
import jade.core.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
   * Activar o desactivar l�mpara de la porter�a seg�n los datos de luminosidad de la porter�a.
* Protege name: EncenderLampara
* @author ontology bean generator
* @version 2019/08/10, 19:10:01
*/

public class EncenderLampara implements AgentAction {
    String puerto;
    SerialPort[] portNames;
    SerialPort sp;
    public EncenderLampara(){
        super();
       
    }
}
