/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controlador;

import Vista.JIFrmCliente;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import Modelo.*;

/**
 *
 * @author Arturo
 */
public class jClsCliente implements ActionListener{
JIFrmCliente frm;

ClsMCliente datos=new ClsMCliente();
    public jClsCliente(JIFrmCliente _frm)
{
    this.frm=_frm;
    this.frm.jbtnAgregar.setActionCommand("agregar");
    this.frm.jbtnAgregar.addActionListener(this);
    this.frm.jbtnEditar.setActionCommand("editar");
    this.frm.jbtnEliminar.setActionCommand("eliminar");
    this.frm.jbtnEliminar.addActionListener(this);
    this.frm.jbtnEditar.addActionListener(this);
    this.frm.jbtnBuscar.setActionCommand("buscar");
    this.frm.jbtnBuscar.addActionListener(this);
    
     this.frm.jtxtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent ke){
                buscar(ke);
            }
            
           
});
    
    
    this.frm.jtxtAm.addKeyListener(new java.awt.event.KeyAdapter() {
    public void keyTyped(java.awt.event.KeyEvent ke){
    am(ke);
}
    });
    
    this.frm.jtxtAp.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyTyped(java.awt.event.KeyEvent ke){
            ap(ke);
        }
});
    this.frm.jtxtDireccion.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyTyped(java.awt.event.KeyEvent ke){
            direccion(ke);
        }
});
    this.frm.jtxtEmail.addKeyListener(new java.awt.event.KeyAdapter() {
public void keyTyped(java.awt.event.KeyEvent ke){
    email(ke);
}
    });
    this.frm.jtxtID.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyTyped(java.awt.event.KeyEvent ke){
            id(ke);
        }
    });
    this.frm.jtxtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyTyped(java.awt.event.KeyEvent ke){
            nombre(ke);
        }
    });
    this.frm.jtxtTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyTyped(java.awt.event.KeyEvent ke){
            telefono(ke);
        }
    });
    
}
    public void frm_load()
    {
        this.frm.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
      String btn=ae.getActionCommand();
        switch(btn){
            case "buscar":
                try{
                    JOptionPane.showMessageDialog(null,"entra");
                    this.datos.setId(this.frm.jtxtID.getText());
                    this.llenartablaBusqueda();
                }catch(Exception e){}
                JOptionPane.showMessageDialog(null,"No existe ese registro");
                break;
            
            
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
                  JOptionPane.showMessageDialog(null,"Error al guardar...");
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

            enviar();
             if(this.datos.editar())
              {
                  JOptionPane.showMessageDialog(null,"Se edito con exito");
                  this.llenartabla();
              }
              else
              {
                  JOptionPane.showMessageDialog(null,"Error al editar...");
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
                  JOptionPane.showMessageDialog(null,"Error al eliminar...");
              }
             }
             catch(Exception e)
             {
                 JOptionPane.showMessageDialog(null, e);
             }
             break;
        }
    }
     public void enviar()
     {
     this.datos.setId(this.frm.jtxtID.getText());
     this.datos.setNombre(this.frm.jtxtNombre.getText());
     this.datos.setAp(this.frm.jtxtAp.getText());
     this.datos.setAm(this.frm.jtxtAm.getText());
     this.datos.setDireccion(this.frm.jtxtDireccion.getText());
     this.datos.setEmail(this.frm.jtxtEmail.getText());
     
     this.datos.setTelefono(this.frm.jtxtTelefono.getText());
     
     }
     private void llenartabla()
    {
            this.frm.jTblCliente.setModel(datos.llenartabla());
    }
      private void llenartablaBusqueda()
    {
            this.frm.jTblCliente.setModel(datos.llenarBusqueda());
    }
    //invocar los metodos
     private void buscar(java.awt.event.KeyEvent  ke) {
        if (this.frm.jtxtBuscar.getText().length()==10)
        {
            ke.consume();
        }
        this.SoloNumeros(ke);
    }
    public void ap(KeyEvent ke){
        if (this.frm.jtxtAp.getText().length()==10)
        {
            ke.consume();
        }
        this.SoloLetras(ke);
    }
    public void am(KeyEvent ke){
        if (this.frm.jtxtAm.getText().length()==10)
        {
            ke.consume();
        }
        this.SoloLetras(ke);
    }
    
     public void direccion(KeyEvent ke){
         if (this.frm.jtxtDireccion.getText().length()==150)
        {
            ke.consume();
        }
     }
      public void email(KeyEvent ke){
         if (this.frm.jtxtEmail.getText().length()==150)
        {
            ke.consume();
        }
     }
       public void id(KeyEvent ke){
         if (this.frm.jtxtID.getText().length()==10)
        {
            ke.consume();
        }
        this.SoloNumeros(ke);
     }
      public void nombre(KeyEvent ke){
         if (this.frm.jtxtNombre.getText().length()==25)
        {
            ke.consume();
        }
        this.SoloLetras(ke);
     }
       public void telefono(KeyEvent ke){
         if (this.frm.jtxtTelefono.getText().length()==13)
        {
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
   
    
}
