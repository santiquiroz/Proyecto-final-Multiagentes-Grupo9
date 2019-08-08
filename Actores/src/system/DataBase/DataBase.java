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
    /**
     * Este metodo realiza una consulta en la base de datos y devuelve un
     * arraylist de arraylist que representan el resultado de la consulta [tabla].
     * 
     *
     * @param SQL Codigo SQLConsulta para nuestra consulta
     * @return Tabla de la consulta
     */
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
    /**
     * Este metodo realiza una insercion en la base de datos por medio de una actualizacion.
     * 
     *
     * @param SQL Codigo SQLConsulta para nuestra insercion.
     */
    public void insert(String SQL){
        try {
             st = conn.getConection().prepareStatement(SQL);
            st.executeUpdate(SQL);
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Este metodo realiza una actualizacion en la base de datos por medio de una actualizacion.
     * 
     *
     * @param SQL Codigo SQLConsulta para nuestra actualizacion.
     * @return entero que informa si la actualizacion fue correcta o no.
     */
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
    /**
     * Este metodo realiza una eliminacion en la base de datos por medio de una actualizacion.
     * 
     *
     * @param SQL Codigo SQLConsulta para nuestra insercion.
     * @return entero que informa si la actualizacion fue correcta o no.
     */
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
    
    /**
     * Este metodo retorna el valor en cadena del elemento en una posicion especificada del resultado .
     * 
     *
     * @param fila fila del valor deseado en la matriz del resultado de consulta.
     * @param columna columna del valor deseado en la matriz del resultado de consulta.
     * @return valor string del elemento en la posicion especificada.
     */
    public String getValueOn(int fila, int columna){
        return String.valueOf(((ArrayList)resultado.get(columna)).get(fila));
    }
    /**
     * Este metodo busca obtiene un arraylist con los nombres en orden de cada columna.
     * 
     * @return arraylist con los nombres en orden de cada columna.
     */
    public ArrayList getColumnNames(){
        return nombresColumnas;
    }
    /**
     * Este metodo obtiene el numero de columnas en la ultima consulta.
     * 
     * @return entero que representa la cantidad de columnas de la ultima .
     */
    public int getColumnNum(){
        return numColumnas;
    }
    /**
     * Este metodo obtiene el arraylist de arraylist que representa la ultima consulta realizada.
     * 
     * @return arraylist de arraylist..
     */
    public ArrayList getLastResult(){
        return resultado;
    }
    /**
     * Este metodo obtiene el ResultSet que representa la ultima consulta realizada.
     * 
     * @return ResultSet srolleable que representa la ultima consulta..
     */
    public ResultSet getLastResultSet(){
        return consulta;
    }
    
}
