/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.MBD;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.UIManager;
import Vista.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import org.jvnet.substance.SubstanceLookAndFeel;

/**
 *
 * @author Arturo
 */
public class CSplash extends javax.swing.JFrame implements Runnable{
    private Timer timer;
    GregorianCalendar tiempo=new GregorianCalendar();
    int hora,minutos,segundos;
    
    MBD con=new MBD();
    public Timer objetotimer;
    Splash fram=null;
    public int a;
    Thread t;
    public CSplash(Splash _frm)
    {
        fram=_frm;
     
     
    }
    private void formWindowOpened(java.awt.event.WindowEvent eve){
        
        
        
    }
    public void frm_load()
    {

    fram.setTitle("San Juanita - Principal");
        fram.setVisible(true);
        run();
    }
    
    
    
    
     public void run()
    {
        objetotimer = new Timer(50,new claseTimer());
        try {
            objetotimer.start();
            fram.setLocationRelativeTo(null);
            fram.setVisible(true);
            t.sleep(5500);
            fram.dispose();

                if(con.conectar())//una vez optenida las variables enviar ala conexion
                {
                    try{
                    JFrmMenuPrincipal frm=new JFrmMenuPrincipal();
                    CMPrincipal cp=new CMPrincipal(frm);
                   
                    cp.Form_Load();
                    }
                    catch(Exception e){
                    JOptionPane.showMessageDialog(this,"error con el servidor"+e);
                    }
                        
                }
                else
                {
                    JFrmDatosConexion conexion=new JFrmDatosConexion();
                    CDatosConexion co=new CDatosConexion(conexion);
                    co.frm_load();
                    
                }
            
        }
  
        catch (InterruptedException ex) {
            Logger.getLogger(CSplash.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public class claseTimer implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
          a= fram.jProgressBar1.getValue();
          if (a <100){
              a++;
              fram.jProgressBar1.setValue(a);
               }
          }                  
        }
}