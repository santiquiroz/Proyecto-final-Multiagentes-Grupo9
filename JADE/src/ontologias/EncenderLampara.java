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
        portNames = SerialPort.getCommPorts();
        puerto = portNames[0].getSystemPortName();
        sp = SerialPort.getCommPort(puerto);
        sp.setComPortParameters(9600, 8, 1, 0); // default connection settings for Arduino
        sp = SerialPort.getCommPort(puerto);
        
        sp.openPort();
        try {
            sp.getOutputStream().write(1);
        } catch (IOException ex) {
            Logger.getLogger(EncenderLampara.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            sp.getOutputStream().flush();
        } catch (IOException ex) {
            Logger.getLogger(EncenderLampara.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
