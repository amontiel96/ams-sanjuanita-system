/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import Modelo.*;
import Vista.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Arturo
 */
public class jClsVentas implements ActionListener,MouseListener{
    JIFrmVentas frm;
    Cls_MVenta datos;
    public jClsVentas(JIFrmVentas _frm,Cls_MVenta _datos){
        this.frm=_frm;
        this.datos=_datos;
        this.frm.jbtnOrdenar.setActionCommand("ordenar");
        this.frm.jbtnOrdenar.addActionListener(this);
        this.frm.jbtnGuardarVenta.setActionCommand("guardar");
        this.frm.jbtnGuardarVenta.addActionListener(this);
        this.frm.jbtnBuscar.setActionCommand("buscar");
        this.frm.jbtnBuscar.addActionListener(this);
        this.frm.jbtnCalcular.setActionCommand("calcular");
        this.frm.jbtnCalcular.addActionListener(this);
        this.frm.jtblProducto.addMouseListener(this);
        this.frm.jtxtIDVENTA.setEnabled(true);
        this.llenartabla();
    }
    public void frm_load(){
        this.frm.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
         String btn=ae.getActionCommand();
        switch(btn){
            case "calcular":
                this.datos.cambio();
                break;
            case "guardar":
                try{
                    this.datos.guardarVenta();
                }catch(Exception e){}
                
                break;
            case "cobrar":
                this.datos.cobrar();
                break;
            case "buscar":
                try{
                    JOptionPane.showMessageDialog(null, "entra");
                    this.datos.setBuscar(this.frm.jtxtBuscar.getText());
                if(this.datos.BuscarProducto()){
                    JOptionPane.showMessageDialog(null,"Encontrado");
                    
                }
                else{
                JOptionPane.showMessageDialog(null,"NO EXISTE");
                this.llenartabla();
                }
                }catch(Exception e){}
                
                break;
            case "ordenar":
                this.frm.jtblOrdenVenta.removeAll();
                this.datos.setCantidad(Integer.parseInt(this.frm.jtxtCantidad.getText()));
                this.datos.setIdpro(this.frm.jtblProducto.getValueAt(0,0).toString());
                this.datos.setIdventa(this.frm.jtxtIDVENTA.getText());
                this.datos.setNombre(this.frm.jtxtNombre.getText());
                this.datos.setPrecio(Float.parseFloat(this.frm.jtxtPrecio.getText()));
                this.datos.setExistencia(Integer.parseInt(this.frm.jtxtExistencia.getText()));
                if(this.frm.jtxtCantidad.equals(0)){
                    JOptionPane.showMessageDialog(null,"no hay");
                }
                else{
                    this.datos.AgregarTablaProducto();
                this.datos.cobrar();
                }
                
                
                
                break;
        }
    }
 private void llenartabla()
    {
            this.frm.jtblProducto.setModel(datos.llenartabla());
    }
    @Override
    public void mouseClicked(MouseEvent me) {
         if(me.getButton()==1){
            int fila=this.frm.jtblProducto.rowAtPoint(me.getPoint());
            if(fila>-1)
            {
                //this.frm..setText(String.valueOf(this.frm.jtblProductos.getValueAt(fila, 0)));
                this.frm.jtxtNombre.setText(String.valueOf(this.frm.jtblProducto.getValueAt(fila, 1)));
                this.frm.jtxtPrecio.setText(String.valueOf(this.frm.jtblProducto.getValueAt(fila, 7)));
                this.frm.jtxtExistencia.setText(String.valueOf(this.frm.jtblProducto.getValueAt(fila, 2)));
                
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
