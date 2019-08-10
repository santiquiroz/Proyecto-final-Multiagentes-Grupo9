/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system.Arduino;

import com.fazecast.jSerialComm.SerialPort;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author santi
 */
public class ConexionArduino {
    String puerto ;
    SerialPort[] portNames;
    SerialPort sp;
    public ConexionArduino(){
        // populate the drop-down box
	portNames = SerialPort.getCommPorts();
        /*System.out.println("lista de puertos");
        System.out.println(portNames);
        System.out.println("conectando a: "+portNames[0].getSystemPortName());
        */
        puerto = portNames[0].getSystemPortName();
        sp.setComPortParameters(9600, 8, 1, 0); // default connection settings for Arduino
        sp.setComPortTimeouts(SerialPort.TIMEOUT_WRITE_BLOCKING, 0, 0); // block until bytes can be written
        sp = SerialPort.getCommPort(puerto); // device name TODO: must be changed
    }
    public ConexionArduino(String port){
        
        puerto = port;
        sp.setComPortParameters(9600, 8, 1, 0); // default connection settings for Arduino
        sp.setComPortTimeouts(SerialPort.TIMEOUT_WRITE_BLOCKING, 0, 0); // block until bytes can be written
        sp = SerialPort.getCommPort(puerto); // device name TODO: must be changed
    }
    public void enviar(Integer valor) throws IOException {
        
        if (sp.openPort()) {
            System.out.println("Port "+puerto+" is open :)");
        } else {
            System.out.println("Failed to open port "+puerto+" :(");
            return;
        }
        sp.getOutputStream().write(valor.byteValue());
        sp.getOutputStream().flush();
                System.out.println("Sent number: " + valor);
        
        System.out.println(sp.getInputStream());
        for (Integer i = 0; i < 5; ++i) {
            try {
                sp.getOutputStream().write(i.byteValue());
                sp.getOutputStream().flush();
                System.out.println("Sent number: " + i);
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(ConexionArduino.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (sp.closePort()) {
            System.out.println("Port is closed :)");
        } else {
            System.out.println("Failed to close port :(");
            return;
        }
    }
    
    public String recibir(){
        if (sp.openPort()) {
            System.out.println("Port "+puerto+" is open :)");
        } else {
            System.out.println("Failed to open port "+puerto+" :(");
            return "Error al leer los datos";
        }
        InputStream mensaje = sp.getInputStream();
        return mensaje.toString();
    }

}
