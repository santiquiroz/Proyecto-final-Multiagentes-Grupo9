/*
Clase que hace mas facil el ejcutar las consultas y transacciones de la base de datos
 */
package system.DataBase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author santi
 */
public class DataBase {
    
    private Conexion conn;
    private PreparedStatement st;
    private ResultSet resultado;
    
    public DataBase() throws ClassNotFoundException{
        conn = new Conexion();
    }
    public DataBase(String url) throws ClassNotFoundException{
        conn = new Conexion(url);
    }
    public DataBase(String url,String user,String password) throws ClassNotFoundException{
        conn = new Conexion(url,user,password);
    }
    public ResultSet select(String SQL){
        try {
             st = conn.getConection().prepareStatement(SQL);
            resultado = st.executeQuery(SQL);
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }
    public void insert(String SQL){
        try {
             st = conn.getConection().prepareStatement(SQL);
            st.executeQuery(SQL);
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public int update(String SQL){
        int resultadoActualizacion=-1;
        try {
             st = conn.getConection().prepareStatement(SQL);
             resultadoActualizacion = st.executeUpdate(SQL);
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultadoActualizacion;
    }
    public int delete(String SQL){
        int resultadoActualizacion=-1;
        try {
             st = conn.getConection().prepareStatement(SQL);
             resultadoActualizacion = st.executeUpdate(SQL);
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultadoActualizacion;
    }
}
