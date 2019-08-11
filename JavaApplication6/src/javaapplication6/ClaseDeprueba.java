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
import java.io.InputStream;
import java.io.OutputStream;
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
        SerialPortHandler serial = new SerialPortHandler();
       serial.connect("COM2");
       OutputStream serialOut = serial.getSerialOutputStream();
       InputStream serialIn=serial.getSerialInputStream();
       try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       String s = "1";
       byte[] b=new byte[5];
       for (int i = 0 ; i<10; i++){
               System.out.println("write " +i%2);
       	if(i%2==0)
       		serialOut.write(1);
       	else
       		serialOut.write(0);
       	serialOut.flush();

      		try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
       	serialIn.read(b);
       	System.out.print("Reçu ");
       	for (int j = 0; j < b.length; j++) {
				System.out.print(b[j]+ " ");
			}
       	System.out.println();
       	try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

       }  
       serial.disconnect();
    }

}
