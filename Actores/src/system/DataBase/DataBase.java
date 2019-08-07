/*
Clase que hace mas facil el ejcutar las consultas y transacciones de la base de datos
 */
package system.DataBase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author santi
 */
public class DataBase {
    
    private Conexion conn;
    private PreparedStatement st;
    private ArrayList resultado;
    private ArrayList nombresColumnas;
    private int numColumnas;
    private ResultSet consulta;
    public DataBase() {
        try {
            conn = new Conexion();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        resultado=new ArrayList();
        nombresColumnas = new ArrayList();
    }
    public DataBase(String url) {
        try {
            conn = new Conexion(url);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        resultado=new ArrayList();
        nombresColumnas = new ArrayList();
    }
    public DataBase(String url,String user,String password){
        try {
            conn = new Conexion(url,user,password);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        resultado=new ArrayList();
        nombresColumnas = new ArrayList();
    }
    
    public ArrayList select(String SQL){
        try {
             st = conn.getConection().prepareStatement(SQL);
            consulta = st.executeQuery();
            //convirtiendo la consulta a un arreglo
            //obtengo numero de columnas
            numColumnas = consulta.getMetaData().getColumnCount();
            //obteniendo los nombres de columnas(claves del hashmap)
            System.out.println("numero de columnas: "+numColumnas);
            for (int i = 1; i <= numColumnas; i++) {
                String nombre =consulta.getMetaData().getColumnName(i);
                nombresColumnas.add(nombre);
                resultado.add(new ArrayList());
            }
            //recorriendo el resultado de la consulta y guardando este en un ArrayList  
            while (this.consulta.next()) {
                for (int i = 1; i <= numColumnas; i++) {
                    Object valor = consulta.getObject(i);
                    ((ArrayList)resultado.get(i-1)).add(valor);
                }
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }
    
    public void insert(String SQL){
        try {
             st = conn.getConection().prepareStatement(SQL);
            st.executeUpdate(SQL);
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
    
    
    public String getValueOn(int fila, int columna){
        return String.valueOf(((ArrayList)resultado.get(columna)).get(fila));
    }
    
    public ArrayList getColumnNames(){
        return nombresColumnas;
    }
    public ArrayList getLastResult(){
        return resultado;
    }
    public ResultSet getLastResultSet(){
        return consulta;
    }
    public int getColumnNum(){
        return numColumnas;
    }
}
