/*
Esta clase permite inicializar todos los actores del proyecto
 */
package main;

import actores.MovimientoSensor;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import system.DataBase.DataBase;

/**
 *
 * @author santi
 */
public class Main {

    public static void main(String[] args) {
            System.out.println("Inicializando Actores");
            MovimientoSensor sensormovimiento = new MovimientoSensor();
            DataBase db = new DataBase("jdbc:mysql://localhost:3306/datosadmon");
            //db.insert("INSERT INTO `cobro` (`consecutivo`, `fecha`, `cantidad`, `id_usuario`) VALUES ('1234', '2019-08-06 00:00:00', '20.92', 'Santiago')");
        ResultSet resultado = db.select("SELECT * FROM `cobro` WHERE 1");
        String cadena;
        try {
            cadena = resultado.getString("id_usuario");
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println();
    }
    
}
