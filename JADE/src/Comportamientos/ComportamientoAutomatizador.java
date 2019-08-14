package Comportamientos;

import jade.core.behaviours.*;
import static java.lang.Thread.sleep;
//import org.python.core.PyObject;
//import org.python.core.PyString;
//import org.python.util.PythonInterpreter;
import system.DataBase.DataBase;
import system.DataBase.ManipularArchivo;

@SuppressWarnings("serial")
public class ComportamientoAutomatizador extends SimpleBehaviour {
//    String puerto;
//    SerialPort[] portNames;
//    SerialPort sp;
//    Scanner scanner;
    // variable para saber si el estado del sensor de correo cambio de 1.0 a 0.0.
    boolean cambio = false;
    int anterior, presente;
    DataBase db;
    //PythonInterpreter interpreter;

    public ComportamientoAutomatizador() {
        super();
        try {
            ManipularArchivo ma = new ManipularArchivo();
            ma.write("hello.txt", "");
            
            anterior = 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void action() {
        try {
            ManipularArchivo ma = new ManipularArchivo();
            
            ma.write("hello.txt", "1");
            sleep(5000);
            String datos = ma.leerArchivo("hello.txt");
            if ( (datos.equals("1")==false) && (datos.equals("0")==false) && (datos.equals(null)==false) && (datos.equals("")==false) && (datos.isEmpty()==false) ) {
                int meow = datos.length();
                String datos2 = datos.substring(2, meow - 6);
                String[] datos3 = datos2.split(",");
                String luminosidad = (datos3[0].split("="))[1];
                String gente = (datos3[1].split("="))[1];
                String correo = (datos3[2].split("="))[1];
                
                
                //Guardando percepciondes de los sensores
                db = new DataBase("jdbc:mysql://localhost:3306/datosambientales");
                db.insert("INSERT INTO luminosidad (datetimemilis, valor, lugar) VALUES (NOW(), '"+luminosidad+"', 'porteria')");
                db.insert("INSERT INTO movimiento (datetimemilis, valor, lugar) VALUES (NOW(), '"+gente+"', 'porteria'); ");
                
                presente =Math.round(Float.valueOf(correo));
                
                //aqui se atualizan los estados de los correos, en este caso solo vamos a utilizar el buzon para el apartamento 44, pues es solo para una demostracion.
                
                if(anterior == 1 && presente == 0){
                    cambio = true;
                }
                
                if (cambio) {
                    db = new DataBase("jdbc:mysql://localhost:3306/datoscorrespondencia");
                    db.select("SELECT * FROM buzon WHERE apartamento LIKE '44'");
                    boolean estadoCorrespondenciaAnteriro=Boolean.valueOf(db.getValueOn(0,1));
                    int estadoCorrespondenciaNuevo = 1;
                    if (estadoCorrespondenciaAnteriro) {
                        estadoCorrespondenciaNuevo = 0;
                    }
                    db.update("UPDATE `buzon` SET `correspondencia` = '"+estadoCorrespondenciaNuevo+"', `notificado` = '0' WHERE `buzon`.`apartamento` = '44';");
                    cambio = false;
                }
                anterior=presente;
                
                fuzzificar(luminosidad, gente); 
                
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void fuzzificar(String luminosidad, String presencia ){
            int lumini;
            lumini = Integer.parseInt(luminosidad);
            System.out.println(lumini);           
            
        }

    @Override
    public boolean done() {
        // TODO Auto-generated method stub
        return false;
    }

}
