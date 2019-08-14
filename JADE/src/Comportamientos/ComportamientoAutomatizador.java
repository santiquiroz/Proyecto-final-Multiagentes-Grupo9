package Comportamientos;

//import com.fazecast.jSerialComm.SerialPort;
import jade.core.behaviours.*;
//import java.io.IOException;
//import java.io.OutputStream;
//import static java.lang.Thread.sleep;
//import java.util.Scanner;
//import java.util.logging.Level;
//import java.util.logging.Logger;

import org.python.core.PyObject;
import org.python.core.PyString;
import org.python.util.PythonInterpreter;
import system.DataBase.ManipularArchivo;

@SuppressWarnings("serial")
public class ComportamientoAutomatizador extends SimpleBehaviour {
//    String puerto;
//    SerialPort[] portNames;
//    SerialPort sp;
//    Scanner scanner;

    PythonInterpreter interpreter;

    public ComportamientoAutomatizador() {
        super();
        try {
            ManipularArchivo ma = new ManipularArchivo();
            ma.write("hello.txt", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void action() {
        try {
            ManipularArchivo ma = new ManipularArchivo();
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean done() {
        // TODO Auto-generated method stub
        return false;
    }

}
