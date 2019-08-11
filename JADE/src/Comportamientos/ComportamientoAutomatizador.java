package Comportamientos;

import jade.core.behaviours.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import system.Arduino.ConexionArduino;
import system.DataBase.DataBase;

@SuppressWarnings("serial")
public class ComportamientoAutomatizador extends SimpleBehaviour {
    
    ConexionArduino arduino;
    boolean getting = false;
    
    //definicion de tarea para analizar valores de ilumina
    Timer ActionTimer = new Timer();
    TimerTask ActionTask = new TimerTask() {
        public void run() {
            Integer valor = 1;
            try {
                arduino.send(valor);
            } catch (IOException ex) {
                Logger.getLogger(ComportamientoAutomatizador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    };
    // definicion de tarea para leer el puerto serial del arduino
    Timer ReceptionTimer = new Timer();
    TimerTask ReceptionTask = new TimerTask() {
        public void run() {
            System.out.println("paso por aqui");
            System.out.println(arduino.get());
            //arduino.get();
        }   

    };

    public ComportamientoAutomatizador() {
        super();
        arduino = new ConexionArduino();
    }

    @Override
    public void action() {
        if(!getting){
            //ReceptionTimer.scheduleAtFixedRate(ReceptionTask,50,50);
            getting = true;
        }
        arduino.get();
        
    }

    @Override
    public boolean done() {
        // TODO Auto-generated method stub
        return false;
    }

}
