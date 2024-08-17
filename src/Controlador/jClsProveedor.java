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
public class jClsProveedor implements ActionListener{
JIFrmProveedor frm;
ClsMProveedor datos=new ClsMProveedor();
    public jClsProveedor(JIFrmProveedor _frm){
        this.frm=_frm;
        this.frm.jbtnAgregar.setActionCommand("agregar");
        this.frm.jbtnAgregar.addActionListener(this);
        this.frm.jbtnEditar.setActionCommand("editar");
        this.frm.jbtnEliminar.setActionCommand("eliminar");
        this.frm.jbtnEliminar.addActionListener(this);
        this.frm.jbtnEditar.addActionListener(this);
        this.llenartabla();
        this.frm.jtxtID.addKeyListener(new KeyAdapter(){
    public void keyTyped(KeyEvent ke){
        id(ke);
    }
    });
    this.frm.jtxtNombre.addKeyListener(new KeyAdapter(){
    public void keyTyped(KeyEvent ke){
        nombre(ke);
    }
    });
    this.frm.jtxtLocalidad.addKeyListener(new KeyAdapter(){
    public void keyTyped(KeyEvent ke){
        localidad(ke);
    }
    });
    this.frm.jtxtEmail.addKeyListener(new KeyAdapter(){
    public void keyTyped(KeyEvent ke){
        email(ke);
    }
    });
    this.frm.jtxtCP.addKeyListener(new KeyAdapter(){
    public void keyTyped(KeyEvent ke){
        cp(ke);
    }
    });
    this.frm.jtxtTelefono.addKeyListener(new KeyAdapter(){
    public void keyTyped(KeyEvent ke){
        telefono(ke);
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
             //String id,nom;
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
    private void llenartabla()
    {
            this.frm.jTblProveedor.setModel(datos.llenartabla());
    }

     public void nombre(KeyEvent ke){
         if (this.frm.jtxtNombre.getText().length()==25)
        {
            ke.consume();
        }
        this.SoloLetras(ke);
     }
     public void id(KeyEvent ke){
         if (this.frm.jtxtID.getText().length()==10)
        {
            ke.consume();
        }
        this.SoloNumeros(ke);
     }
      public void localidad(KeyEvent ke){
          if(this.frm.jtxtLocalidad.getText().length()==10)
          {
              ke.consume();
          }
          this.SoloLetras(ke);
          
      }
      public void email(KeyEvent ke){
          if(this.frm.jtxtEmail.getText().length()==150){
              ke.consume();
          }
          
      }
    public void cp(KeyEvent ke){
        if(this.frm.jtxtCP.getText().length()==10){
            ke.consume();
        }
        
    }
    public void telefono(KeyEvent ke){
        if(this.frm.jtxtTelefono.getText().length()==13){
            ke.consume();
        }
        this.SoloNumeros(ke);
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
     public void enviar()
     {
     this.datos.setId(this.frm.jtxtID.getText());
     this.datos.setDireccion(frm.jtxtLocalidad.getText());
     this.datos.setTelefono(frm.jtxtTelefono.getText());
     this.datos.setCp(this.frm.jtxtCP.getText());
     this.datos.setNombre(this.frm.jtxtNombre.getText());
     this.datos.setEmail(this.frm.jtxtEmail.getText());
     }
    
}
