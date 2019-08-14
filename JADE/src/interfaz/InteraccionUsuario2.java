package interfaz;

import agentes.AgenteInteraccionUsuarioII;
import java.awt.*;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.JFrame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author David Ospina
 */
public class InteraccionUsuario2 extends JFrame {

    private javax.swing.JButton jButton1 = new JButton("Envar");
    private javax.swing.JLabel jLabel1 = new JLabel("Metas aca tu ID");
    private javax.swing.JTextField jTextField1 = new JTextField();
    private AgenteInteraccionUsuarioII agente;

    public InteraccionUsuario2(final AgenteInteraccionUsuarioII agente) {
        
        super("Ventana meter identificacion");
        
        this.agente = agente;
        
        jTextField1.setPreferredSize(new Dimension(100, 30));
        this.setLayout(new FlowLayout());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.add(jButton1);
        this.add(jLabel1);
        this.add(jTextField1);
        this.pack();
        this.setVisible(true);
        
        jButton1.addActionListener(new ActionListener() {
			
	        public void actionPerformed(ActionEvent e) {
	        	
	            try {
	            	
                        String textoDelCulo = jTextField1.getText();
	            	agente.addPedidorCedula(textoDelCulo);
	            	
	            } catch (Exception ex) {
	                ex.printStackTrace();
	            }
	            
	        }
	    });
        
    }
    

}
