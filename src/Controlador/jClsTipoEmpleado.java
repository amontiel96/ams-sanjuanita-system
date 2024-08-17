/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Vista.*;
import Modelo.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author Arturo
 */
public class jClsTipoEmpleado implements ActionListener{
    JIFrmTipoEmp frm;
    ClsTipoEmpleado datos=new ClsTipoEmpleado();
    public jClsTipoEmpleado(JIFrmTipoEmp _frm){
        this.frm=_frm;
        this.frm.jbtnAgregar.setActionCommand("agregar");
        this.frm.jbtnAgregar.addActionListener(this);
        this.frm.jbtnEditar.setActionCommand("editar");
        this.frm.jbtnEliminar.setActionCommand("eliminar");
        this.frm.jbtnEliminar.addActionListener(this);
        this.frm.jbtnEditar.addActionListener(this);
        this.llenartabla();
        this.frm.jtxtBuscar.addKeyListener(new KeyAdapter(){
        public void keyTyped(KeyEvent ke){
            buscar(ke);
        }
        });
        this.frm.jtxtID.addKeyListener(new KeyAdapter(){
        public void keyTyped(KeyEvent ke){
            id(ke);
        }
        });
        this.frm.jtxtTipo.addKeyListener(new KeyAdapter(){
        public void keyTyped(KeyEvent ke){
            tipo(ke);
        }
        });
        
    }
    public void frm_load(){
        this.frm.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        String btn=ae.getActionCommand();
        switch(btn){
            
            case "agregar":
                JOptionPane.showMessageDialog(null,"si entra");
                try
             {
            enviar();
             if(this.datos.guardarProveedor())
              {
                  JOptionPane.showMessageDialog(null,"Se guardo con exito");
                  this.llenartabla();
              }
              else
              {
                  JOptionPane.showMessageDialog(null,"nose guardo");
              }
             }
             catch(Exception e)
             {
                 JOptionPane.showMessageDialog(null, e);
             }
                break;
            case "editar":
                 try
             {
                 JOptionPane.showMessageDialog(null,"Entra");
            enviar();
             if(this.datos.editar())
              {
                  JOptionPane.showMessageDialog(null,"Se edito con exito");
                  this.llenartabla();
              }
              else
              {
                  JOptionPane.showMessageDialog(null,"nose elimino");
              }
             }
             catch(Exception e)
             {
                 JOptionPane.showMessageDialog(null, e);
             }
                break;
                case "eliminar":
              try
             {
            enviar();
             if(this.datos.eliminar()==true)
              {
                  JOptionPane.showMessageDialog(null,"Se elimino con exito");
                  this.llenartabla();
              }
             else
              {
                  JOptionPane.showMessageDialog(null,"nose elimino");
              }
             }
             catch(Exception e)
             {
                 JOptionPane.showMessageDialog(null, e);
             }
             break;
    }
    }
    public void buscar(KeyEvent ke){
        if(this.frm.jtxtBuscar.getText().length()==10){
            ke.consume();
        }
        this.SoloNumeros(ke);
    }
    public void id(KeyEvent ke){
        if(this.frm.jtxtID.getText().length()==10){
            ke.consume();
        }
        this.SoloNumeros(ke);
    }
    public void tipo(KeyEvent ke){
        if(this.frm.jtxtTipo.getText().length()==10){
            ke.consume();
        }
        this.SoloLetras(ke);
    }

     public void SoloNumeros(KeyEvent e) {
        char teclaPulsada = e.getKeyChar();
        if (!Character.isDigit(teclaPulsada)) { 
            e.consume(); 
            }                     
    }
     public void SoloLetras(KeyEvent e) {
        char teclaPulsada = e.getKeyChar();
        if (Character.isDigit(teclaPulsada)) {
            e.consume(); 
            }                      
    }
     public void enviar(){
         this.datos.setId(this.frm.jtxtID.getText());
         this.datos.setTipo(this.frm.jtxtTipo.getText());
     }
      private void llenartabla()
    {
            this.frm.jTblTipoEmp.setModel(datos.llenartabla());
    }
    
}
