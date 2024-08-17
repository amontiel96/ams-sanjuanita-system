/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Vista.JIFrmEmpleado;
import Modelo.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Arturo
 */
public class jClsEmpleado implements ActionListener, MouseListener {
JIFrmEmpleado frm;
ClsMEmpleado datos;
MBD comb=new MBD();
public jClsEmpleado(JIFrmEmpleado _frm, ClsMEmpleado _datos) throws ClassNotFoundException{
    this.frm=_frm;
    this.datos=_datos;
    
    this.frm.jbtnAgregar.setActionCommand("agregar");
    this.frm.jbtnAgregar.addActionListener(this);
   
    this.frm.jbtnEditar.setActionCommand("editar");
    this.frm.jbtnEliminar.setActionCommand("eliminar");
    this.frm.jbtnEliminar.addActionListener(this);
    this.frm.jbtnEditar.addActionListener(this);
    this.frm.jTable1.addMouseListener(this);
    this.datos.CargarCombos();
    
    this.frm.jtxtAm.addKeyListener(new KeyAdapter() {
        public void keyTyped(KeyEvent ke){
            am(ke);
        }
        });
        this.frm.jtxtAp.addKeyListener(new KeyAdapter() {
        public void keyTyped(KeyEvent ke){
            ap(ke);
        }
        });
        this.frm.jtxtDireccion.addKeyListener(new KeyAdapter() {
        public void keyTyped(KeyEvent ke){
            direccion(ke);
        }
        });
        this.frm.jtxtEmail.addKeyListener(new KeyAdapter(){
        public void keyTyped(KeyEvent ke){
            email(ke);
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
        this.frm.jtxtTelefono.addKeyListener(new KeyAdapter(){
        public void keyTyped(KeyEvent ke){
            telefono(ke);
        }
        });
        this.frm.jtxtBuscar.addKeyListener(new KeyAdapter(){
        public void keyTyped(KeyEvent ke){
            buscar(ke);
        }
        });
}
public void frm_load()
{
    frm.setVisible(true);
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
   
     public void enviar()
     {
     this.datos.setId(this.frm.jtxtID.getText());
     this.datos.setNombre(this.frm.jtxtNombre.getText());
     this.datos.setAp(this.frm.jtxtAp.getText());
     this.datos.setAm(this.frm.jtxtAm.getText());
     this.datos.setDireccion(this.frm.jtxtDireccion.getText());
     this.datos.setEmail(this.frm.jtxtEmail.getText());
     this.datos.setPwd(this.frm.jtxtPwd.getText());
     this.datos.setTelefono(this.frm.jtxtTelefono.getText());
    this.datos.setTipo((String) this.frm.jcmbTipo.getSelectedItem());
     }
     private void llenartabla()
    {
            this.frm.jTable1.setModel(datos.llenartabla());
    }
     public void llenarcombo(){
    this.frm.jcmbTipo.removeAllItems();
        String[][] res = datos.LlenarCombo();
        int tam = datos.LlenarCombo().length;
        for(int x=0; x< tam; x++)
        {
            this.frm.jcmbTipo.addItem(res[x][0]);
        }
        
    }
    
    private void buscar(KeyEvent ke) {
        if (this.frm.jtxtBuscar.getText().length()==8)
        {
            ke.consume();
        }
        this.SoloNumeros(ke);
    }
    public void ap(KeyEvent ke){
        if (this.frm.jtxtAp.getText().length()==30)
        {
            ke.consume();
        }
        this.SoloLetras(ke);
    }
     public void am(KeyEvent ke){
        if (this.frm.jtxtAm.getText().length()==30)
        {
            ke.consume();
        }
        this.SoloLetras(ke);
    }
     public void direccion(KeyEvent ke){
         if (this.frm.jtxtDireccion.getText().length()==250)
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
         if (this.frm.jtxtID.getText().length()==8)
        {
            ke.consume();
        }
        this.SoloNumeros(ke);
     }
      public void nombre(KeyEvent ke){
         if (this.frm.jtxtNombre.getText().length()==30)
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
     public void pwd(KeyEvent ke){
         if (this.frm.jtxtPwd.getText().length()==8)
        {
            ke.consume();
        }
        
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

    @Override
    public void mouseClicked(MouseEvent me) {
         if(me.getButton()==1)
        {
            int fila=this.frm.jTable1.rowAtPoint(me.getPoint());
           
            if(fila>-1)
            {
                this.frm.jtxtID.setText(String.valueOf(this.frm.jTable1.getValueAt(fila,0)));
                this.frm.jtxtNombre.setText(String.valueOf(this.frm.jTable1.getValueAt(fila,1)));
                this.frm.jtxtAp.setText(String.valueOf(this.frm.jTable1.getValueAt(fila,2)));
                this.frm.jtxtAm.setText(String.valueOf(this.frm.jTable1.getValueAt(fila,3)));
                this.frm.jtxtDireccion.setText(String.valueOf(this.frm.jTable1.getValueAt(fila,4)));
                this.frm.jtxtEmail.setText(String.valueOf(this.frm.jTable1.getValueAt(fila,5)));
                this.frm.jtxtTelefono.setText(String.valueOf(this.frm.jTable1.getValueAt(fila,6)));
                this.frm.jtxtPwd.setText(String.valueOf(this.frm.jTable1.getValueAt(fila,7)));
               
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

