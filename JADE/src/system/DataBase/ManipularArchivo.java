/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system.DataBase;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


/**
 *
 * @author David Ospina
 */
public class ManipularArchivo {

    public String leerArchivo(String url) {
        File archivo;
        FileReader fr = null;
        BufferedReader br;
        String texto = "";
        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            archivo = new File(url);
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
            // Lectura del fichero
            String linea = "puta vida";
            while ((linea = br.readLine()) != null) {
                texto = texto + linea + '\n';
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // En el finally cerramos el fichero, para asegurarnos
            // que se cierra tanto si todo va bien como si salta 
            // una excepcion.
            try {
                if (null != fr) {
                    fr.close();
                }

            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return texto;

    }

    public void write(String archivo, String datos) throws IOException {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(archivo));
            bw.write(datos);
            bw.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
