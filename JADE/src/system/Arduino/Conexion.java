/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system.Arduino;

import com.fazecast.jSerialComm.SerialPort;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author santi
 */
public class Conexion {

    public void conectar() throws IOException {
        SerialPort sp = SerialPort.getCommPort("COM4 (Arduino/Genuino Uno"); // device name TODO: must be changed
        sp.setComPortParameters(9600, 8, 1, 0); // default connection settings for Arduino
        sp.setComPortTimeouts(SerialPort.TIMEOUT_WRITE_BLOCKING, 0, 0); // block until bytes can be written

        if (sp.openPort()) {
            System.out.println("Port is open :)");
        } else {
            System.out.println("Failed to open port :(");
            return;
        }
        
        System.out.println(sp.getInputStream());
        for (Integer i = 0; i < 5; ++i) {
            try {
                sp.getOutputStream().write(i.byteValue());
                sp.getOutputStream().flush();
                System.out.println("Sent number: " + i);
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (sp.closePort()) {
            System.out.println("Port is closed :)");
        } else {
            System.out.println("Failed to close port :(");
            return;
        }
    }

}
