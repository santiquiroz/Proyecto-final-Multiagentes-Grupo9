/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system.Arduino;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortEvent;
import com.fazecast.jSerialComm.SerialPortPacketListener;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import org.jfree.io.IOUtils;

/**
 *
 * @author santi
 */
public class ConexionArduino {

    String puerto;
    SerialPort[] portNames;
    SerialPort sp;
    boolean hasListener = false;
    
    
    public ConexionArduino() {
        // populate the drop-down box
        portNames = SerialPort.getCommPorts();
        /*System.out.println("lista de puertos");
        System.out.println(portNames);
        System.out.println("conectando a: "+portNames[0].getSystemPortName());
         */
        puerto = portNames[0].getSystemPortName();
        sp = SerialPort.getCommPort(puerto);
        sp.setComPortParameters(9600, 8, 1, 0); // default connection settings for Arduino
        //sp.setComPortTimeouts(SerialPort.TIMEOUT_WRITE_BLOCKING, 0, 0); // block until bytes can be written
        sp = SerialPort.getCommPort(puerto); // device name TODO: must be changed
        
        
        
    }

    public ConexionArduino(String port) {

        puerto = port;
        sp = SerialPort.getCommPort(puerto);
        sp.setComPortParameters(9600, 8, 1, 0); // default connection settings for Arduino
        //sp.setComPortTimeouts(SerialPort.TIMEOUT_WRITE_BLOCKING, 0, 0); // block until bytes can be written
        sp = SerialPort.getCommPort(puerto); // device name TODO: must be changed
    }

    public void send(Integer valor) throws IOException {

        if (sp.openPort()) {
            //System.out.println("Port "+puerto+" is open :)");
        } else {
            //System.out.println("Failed to open port "+puerto+" :(");
            return;
        }
        sp.getOutputStream().write(valor.byteValue());
        sp.getOutputStream().flush();
        //System.out.println("Sent number: " + valor);

        if (sp.closePort()) {
            //System.out.println("Port is closed :)");
        } else {
            //System.out.println("Failed to close port :(");
            return;
        }
    }

    public String get() {
        if (sp.openPort()) {
            //System.out.println("Port "+puerto+" is open :)");
            
        } else {
            //System.out.println("Failed to open port "+puerto+" :(");
            return "Error al leer los datos";
        }
        InputStream mensaje = sp.getInputStream();
        
        
        System.out.println(mensaje);
        try {
            System.out.println(Arrays.toString(sp.getInputStream().readAllBytes()));
        } catch (IOException ex) {
            Logger.getLogger(ConexionArduino.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        /*if(sp.closePort()) {
            //System.out.println("Port is closed :)");
        } else {
            //System.out.println("Failed to close port :(");
            return "Error al leer los datos";
        }
        */
        if (mensaje == null) {
            return "";
        }
        
        
        
        return (mensaje.toString());
        
        
        /*if(!hasListener){
            PacketListener listener = new PacketListener();
            System.out.println("anadiendo listener");
            sp.addDataListener(listener); 
            hasListener = true;
        }*/
      
        
    }

    private final class PacketListener implements SerialPortPacketListener {

        @Override
        public int getListeningEvents() {
            return SerialPort.LISTENING_EVENT_DATA_RECEIVED;
        }

        @Override
        public int getPacketSize() {
            return 100;
        }

        @Override
        public void serialEvent(SerialPortEvent event) {
            byte[] newData = event.getReceivedData();
            System.out.println("Received data of size: " + newData.length);
            for (int i = 0; i < newData.length; ++i) {
                System.out.print((char) newData[i]);
            }
            System.out.println("\n");
        }

    }
}
