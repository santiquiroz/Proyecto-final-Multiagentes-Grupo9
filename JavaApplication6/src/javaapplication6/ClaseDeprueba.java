/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication6;

import Java_Arduino.ArduinoTX.JavaTX;
import com.fazecast.jSerialComm.SerialPort;
import com.panamahitek.ArduinoException;
import com.panamahitek.PanamaHitek_Arduino;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import jssc.SerialPortException;

/**
 *
 * @author santi
 */
public class ClaseDeprueba {

    PanamaHitek_Arduino ino;
    String puerto;
    SerialPort[] portNames;
    SerialPort sp;

    public ClaseDeprueba() {
        while (true) {
            System.out.println("Escriba el valor de encendido ");
            Scanner scanner = new Scanner(System.in);
            
            portNames = SerialPort.getCommPorts();
            puerto = portNames[0].getSystemPortName();
            sp = SerialPort.getCommPort(puerto);
            sp.setComPortParameters(9600, 8, 1, 0); // default connection settings for Arduino
            sp = SerialPort.getCommPort(puerto);

            sp.openPort();
            try {
                sp.getOutputStream().write(scanner.nextInt());
            } catch (IOException ex) {
                Logger.getLogger(ClaseDeprueba.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                sp.getOutputStream().flush();
            } catch (IOException ex) {
                Logger.getLogger(ClaseDeprueba.class.getName()).log(Level.SEVERE, null, ex);
            }
            sp.closePort();
        }

    }

}
