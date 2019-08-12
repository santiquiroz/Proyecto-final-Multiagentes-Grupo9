package Comportamientos;

import com.fazecast.jSerialComm.SerialPort;
import jade.core.behaviours.*;
import java.io.IOException;
import java.io.OutputStream;
import static java.lang.Thread.sleep;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

@SuppressWarnings("serial")
public class ComportamientoAutomatizador extends SimpleBehaviour {
    String puerto;
    SerialPort[] portNames;
    SerialPort sp;
    Scanner scanner;
    
    public ComportamientoAutomatizador(){
        super();
        portNames = SerialPort.getCommPorts(); 
         puerto = portNames[0].getSystemPortName();
         sp = SerialPort.getCommPort(puerto);
         sp.setComPortParameters(9600, 8, 1, 0);
         sp.openPort();
    }

    @Override
    public void action() {
        
         
        
        //mandando solicitud de entrega de datos
        
        OutputStream outputStream = sp.getOutputStream();
        try {
            outputStream.write(1);
            outputStream.close();
        } catch (IOException ex) {
            Logger.getLogger(ComportamientoAutomatizador.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            sleep(100);
       } catch (InterruptedException ex) {
            Logger.getLogger(ComportamientoAutomatizador.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("ya desperte de suenom");
        
            
         scanner = new Scanner(sp.getInputStream());
         //System.out.println(scanner.next());
         System.out.println("linea");
         while(scanner.hasNext()){
             System.out.println(scanner.nextLine());
         }
    }

    @Override
    public boolean done() {
        // TODO Auto-generated method stub
        return false;
    }

}
