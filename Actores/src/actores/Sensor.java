/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actores;

/**
 *
 * @author santi
 */
public  class Sensor {
    private String lugar;
    private Float valor;
    public String getLugar(){
       return lugar; 
    }
    public void setLugar(String ubicacion){
        lugar = ubicacion;
    }
}
