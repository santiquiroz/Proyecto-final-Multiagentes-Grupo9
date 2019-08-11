/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication6;

import com.panamahitek.PanamaHitek_Arduino;

public class JavaRXTX {

   
    PanamaHitek_Arduino ino = new PanamaHitek_Arduino();
    public static void main(String args[]) {
        System.out.println("puto el que lo lea");
        new ClaseDeprueba();
    }
}
