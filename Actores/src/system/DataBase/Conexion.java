/*
Clase que sirve para realizar una conexion a una base de datos mysql 
con la direccion, usuario y contraseña establecidos en el constructor.
 */
package system.DataBase;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author santi
 */
public class Conexion {
    private static Connection conn;
    private static final String driver = "com.mysql.jdbc.Driver";
    private String user = "root";
    private String password = "";
    private String url = "jdbc:mysql://localhost:3306/datosusuario";
    
    public Conexion () throws ClassNotFoundException{
        conn = null;
        try{
            Class.forName(driver);
            conn = (Connection) DriverManager.getConnection(url,user,password);
            if(conn!= null){
                System.out.println("conexion establecida!");
            }
        } catch (SQLException e) { 
            System.out.println("conexion fallida: " + e);
        }
    }
    public Conexion (String url) throws ClassNotFoundException{
        this.url = url;
        conn = null;
        try{
            Class.forName(driver);
            conn = (Connection) DriverManager.getConnection(url,user,password);
            if(conn!= null){
                System.out.println("conexion establecida!");
            }
        }catch (SQLException e) {
            System.out.println("conexion fallida: "+e);
        }
    }
    public Conexion (String url, String user, String password) throws ClassNotFoundException{
        this.url = url;
        this.user = user;
        this.password = password;
        conn = null;
        try{
            Class.forName(driver);
            conn = (Connection) DriverManager.getConnection(url,user,password);
            if(conn!= null){
                System.out.println("conexion establecida!");
            }
        }catch (SQLException e) {
            System.out.println("conexion fallida: "+e);
        }
    }
    
    /*
    metodo para obtener la conexion activa a la base de datos.
    */
    public Connection getConection(){
        return conn;
    }
    /*
    metodo para terminar la conexion a la base de datos activa.
    */
    public void desconectar(){
        conn = null;
        if (conn == null) {
            System.out.println("conexion terminada con exito !");
        }
    }
    /*
    Ejemplo de coenctar
    Conexion con;
    con = new Conectar();
    Connection reg = con.getConnection();
    */
    /*
    Ejemplo de desconectar
    con.desconectar();
    */
}