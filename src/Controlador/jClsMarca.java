/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Vista.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import Modelo.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
/**
 *
 * @author Arturo
 */
public class jClsMarca implements ActionListener, MouseListener{
JIFrmMarca frm;
ClsM_Marca datos=new ClsM_Marca();
    public jClsMarca(JIFrmMarca _frm)
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
    this.frm.jTblMarca.addMouseListener(this);
    
    
    this.llenartabla();
    this.frm.jtxtBuscar.addKeyListener(new KeyAdapter() {
    public void keyTyped(KeyEvent ke){
        buscar(ke);
    }
    });
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
}
    public void frm_load(){
        this.frm.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        String btn=ae.getActionCommand();
        switch(btn){
            case "buscar":
                JOptionPane.showMessageDialog(frm, "entra");
                 int id=frm.jtxtBuscar.getText().trim().length();
                
                if(id==0)
                {
                    JOptionPane.showMessageDialog(frm,"Error, No ha introducido clave");
                }
                else
                {
                    this.datos.setBuscar(this.frm.jtxtBuscar.getText());
                    
                    if(this.datos.buscar()==true)
                    {
                        JOptionPane.showMessageDialog(frm, "registro encontrado");
                        this.llenartabla();
                    }
                    else{
                        JOptionPane.showMessageDialog(frm, "registro no existe");
                    }
                }
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
     private void llenartabla()
    {
            this.frm.jTblMarca.setModel(datos.llenartabla());
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
      private void buscar(java.awt.event.KeyEvent  ke) {
        if (this.frm.jtxtBuscar.getText().length()==10)
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
     public void enviar()
     {
     this.datos.setId(this.frm.jtxtID.getText());
             this.datos.setNombre(this.frm.jtxtNombre.getText());
     }

    @Override
    public void mouseClicked(MouseEvent me) {
        //codigo para al dar clic en la tabla se llenene los campos
        if(me.getButton()==1){
            int fila=this.frm.jTblMarca.rowAtPoint(me.getPoint());
            if(fila>-1)
            {
                this.frm.jtxtID.setText(String.valueOf(this.frm.jTblMarca.getValueAt(fila, 0)));
                this.frm.jtxtNombre.setText(String.valueOf(this.frm.jTblMarca.getValueAt(fila, 1)));
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent me) {
        
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        
    }

    @Override
    public void mouseExited(MouseEvent me) {
        
    }
}
