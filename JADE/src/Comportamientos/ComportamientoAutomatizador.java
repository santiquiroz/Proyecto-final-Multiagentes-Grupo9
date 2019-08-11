package Comportamientos;

import com.fazecast.jSerialComm.SerialPort;
import jade.core.behaviours.*;
import java.io.IOException;
import java.io.OutputStream;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import system.Arduino.ConexionArduino;
import system.DataBase.DataBase;

@SuppressWarnings("serial")
public class ComportamientoAutomatizador extends SimpleBehaviour {
    String puerto;
    SerialPort[] portNames;
    SerialPort sp;
    Scanner scanner;

    @Override
    public void action() {
        portNames = SerialPort.getCommPorts(); 
         puerto = portNames[0].getSystemPortName();
         sp = SerialPort.getCommPort(puerto);
         sp.setComPortParameters(9600, 8, 1, 0);
         
        //mandando solicitud de entrega de datos
        sp.openPort();
        OutputStream serialOut = sp.getOutputStream();
        try {
            serialOut.write("hola".getBytes());
            //serialOut.flush();
        } catch (IOException ex) {
            Logger.getLogger(ComportamientoAutomatizador.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //sp.closePort();
        
        try {
            sleep(100);
        } catch (InterruptedException ex) {
            Logger.getLogger(ComportamientoAutomatizador.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("ya desperte de suenom");
        
        //sp.openPort();
         scanner = new Scanner(sp.getInputStream());
         //System.out.println(scanner.next());
         System.out.println("linea");
         while(scanner.hasNext()){
             System.out.println(scanner.nextLine());
         }
         
         //sp.closePort(); 
    }

    @Override
    public boolean done() {
        // TODO Auto-generated method stub
        return false;
    }

}
