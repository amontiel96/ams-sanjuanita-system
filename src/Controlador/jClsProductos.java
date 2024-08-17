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
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Arturo
 */
public class jClsProductos implements ActionListener {
    JIFrmProducto frm;
    ClsMProductos datos;
    public jClsProductos(JIFrmProducto _frm,ClsMProductos _datos) throws ClassNotFoundException{
        this.frm=_frm;
        this.datos=_datos;
         this.frm.jbtnAgregar.setActionCommand("agregar");
        this.frm.jbtnAgregar.addActionListener(this);
        this.frm.jbtnEditar.setActionCommand("editar");
        this.frm.jbtnEliminar.setActionCommand("eliminar");
        this.frm.jbtnEliminar.addActionListener(this);
        this.frm.jbtnEditar.addActionListener(this);
        this.frm.jbtnBuscar.setActionCommand("buscar");
        this.frm.jbtnBuscar.addActionListener(this);
        this.llenartabla();
        this.datos.CargarCombos();
    }
    public void frm_load(){
        this.frm.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
     String btn=ae.getActionCommand();
        switch(btn){
            case "buscar":
                try{
                    this.datos.setBuscar(this.frm.jtxtBuscar.getText());
                    if(this.datos.BuscarProducto()==true){
                    JOptionPane.showMessageDialog(null, "RESGISTRO ENCONTRADO");
                    }
                    else{
                    JOptionPane.showMessageDialog(null, "REGISTRO NO EXISTE");
                    this.llenartabla();
                    }
                    
                    
                }catch(Exception e){}
               
                break;
            case "agregar":
                try
             {

            enviar();
             if(this.datos.guardarProveedor()==true)
              {
                  JOptionPane.showMessageDialog(null,"Se guardo con exito");
                  this.llenartabla();
              }
              else
              {
                  JOptionPane.showMessageDialog(null,"nose guardo");
                  this.llenartabla();
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
            this.frm.jTblProducto.setModel(datos.llenartabla());
    }
  
     
     
    public void enviar(){
        this.datos.setId(this.frm.jtxtID.getText());
        this.datos.setNombre(this.frm.jtxtNombre.getText());
        this.datos.setExistencia(Integer.parseInt(this.frm.jtxtExistencia.getText()));
        this.datos.setDescripcion(this.frm.jtxtDescripcion.getText());
        this.datos.setIdmarca((String) this.frm.jcmbMarca.getSelectedItem());
        this.datos.setIdcategoria((String) this.frm.jcmbCategoria.getSelectedItem());
        
    }

}
