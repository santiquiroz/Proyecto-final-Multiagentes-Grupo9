package Comportamientos;

import java.util.ArrayList;

import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import system.DataBase.DataBase;

public class ComportamientoBroadcast extends TickerBehaviour{

	public ComportamientoBroadcast(Agent a, long period) {
		super(a, period);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onTick() {
		try {
			String[] resultados;
			resultados = consultarEventos();
			if ( resultados != null ) {
				int tamano = resultados.length;
				for (int i = 0; i < tamano; i++) {
					System.out.println(resultados[i]);
		        	
		        }
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public String[] consultarEventos() {
		
		DataBase db = new DataBase("jdbc:mysql://localhost:3306/datosadmon");
        ArrayList resultado = db.select("SELECT * FROM evento WHERE notificado = 0");
        int tamano = ((ArrayList) (resultado.get(0))).size();
        if (tamano > 0) {
            String[] resultadoReduntante = new String[tamano];
            for (int i = 0; i < tamano; i++) {
            	resultadoReduntante[i]=db.getValueOn(0, i);
            }
            return resultadoReduntante;
        }

	
		return null;
		
	}

}
