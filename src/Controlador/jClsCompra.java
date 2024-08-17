/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Modelo.*;
import Vista.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Arturo
 */
public class jClsCompra implements ActionListener,MouseListener{
    JIFrmCompra frm;
    ClsMCompra datos;
    public int cont=0;
    public jClsCompra(JIFrmCompra _frm,ClsMCompra _datos) throws ClassNotFoundException{
        this.frm=_frm;
        this.datos=_datos;
        this.frm.jbtnBuscar.setActionCommand("buscar");
        this.frm.jbtnBuscar.addActionListener(this);
        this.frm.jbtnOrdenar.setActionCommand("ordenar");
        this.frm.jbtnOrdenar.addActionListener(this);
        this.frm.jbtnCobrar.setActionCommand("cobrar");
        this.frm.jbtnCobrar.addActionListener(this);
        this.frm.jbtnGuadar.setActionCommand("guardar");
        this.frm.jbtnGuadar.addActionListener(this);
        this.frm.jtblProductos.addMouseListener(this);
        this.datos.CargarCombos();
        this.llenartabla();
    }
    public void frm_load(){
        this.frm.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        String btn=ae.getActionCommand();
        switch(btn){
            case "guardar":
                try{
                    this.datos.guardarCompra();
                    this.datos.setCont(cont);
                }catch(Exception e){}
                
                break;
            case "cobrar":
                this.datos.cobrar();
                break;
            case "buscar":
                try{
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
                this.frm.jtblOrden.removeAll();
                this.datos.setCantidad(Integer.parseInt(this.frm.jtxtCantidad.getText()));
                this.datos.setIdpro(this.frm.jtxtID.getText());
                this.datos.setIdventa(this.frm.jtxtIDVENTA.getText());
                this.datos.setNombre(this.frm.jtxtNombre.getText());
                this.datos.setPrecio(Float.parseFloat(this.frm.jtxtPrecio.getText()));
                this.datos.setExistencia(Integer.parseInt(this.frm.jtxtExistencia.getText()));
                this.datos.setIdprove((String) this.frm.jcmbProveedor.getSelectedItem());
                if(this.frm.jtxtCantidad.equals(0)){
                    JOptionPane.showMessageDialog(null,"no hay");
                }
                else{
                    this.datos.AgregarTablaProducto();
                cont++;
                this.datos.cobrar();
                }
                
                
                
                break;
        }
    }

    private void llenartabla()
    {
            this.frm.jtblProductos.setModel(datos.llenartabla());
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        if(me.getButton()==1){
            int fila=this.frm.jtblProductos.rowAtPoint(me.getPoint());
            if(fila>-1)
            {
                this.frm.jtxtID.setText(String.valueOf(this.frm.jtblProductos.getValueAt(fila, 0)));
                this.frm.jtxtNombre.setText(String.valueOf(this.frm.jtblProductos.getValueAt(fila, 1)));
                this.frm.jtxtPrecio.setText(String.valueOf(this.frm.jtblProductos.getValueAt(fila, 7)));
                this.frm.jtxtExistencia.setText(String.valueOf(this.frm.jtblProductos.getValueAt(fila, 2)));
                
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
